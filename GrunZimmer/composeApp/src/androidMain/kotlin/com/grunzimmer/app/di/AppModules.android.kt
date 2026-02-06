package com.grunzimmer.app.di

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.grunzimmer.app.ActivityProvider
import com.grunzimmer.app.domain.repository.IAuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await // ENSURE THIS IMPORT EXISTS
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

actual val platformModule = module {
    single<IAuthRepository> { AndroidAuthRepository() }
}

class AndroidAuthRepository : IAuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private var storedVerificationId: String? = null

    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        val activity = ActivityProvider.getActivity()
            ?: return Result.failure(Exception("Activity not found"))

        return try {
            callbackFlow {
                val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) trySend(Result.success(Unit))
                                else trySend(Result.failure(task.exception ?: Exception("Sign in failed")))
                            }
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        trySend(Result.failure(e))
                    }

                    override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                        storedVerificationId = verificationId
                        trySend(Result.success(Unit))
                    }
                }

                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(callbacks)
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(options)

                awaitClose { /* Cleanup if needed */ }
            }.first()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        return try {
            val verificationId = storedVerificationId
                ?: return Result.failure(Exception("Verification ID missing"))

            val credential = PhoneAuthProvider.getCredential(verificationId, code)

            // This await() now requires the library added above
            auth.signInWithCredential(credential).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getUserId(): String? {
        return auth.currentUser?.uid
    }
}