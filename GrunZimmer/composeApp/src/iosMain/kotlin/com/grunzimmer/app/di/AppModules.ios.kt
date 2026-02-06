package com.grunzimmer.app.di

import com.grunzimmer.app.domain.repository.IAuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import org.koin.dsl.module

actual val platformModule = module {
    single<IAuthRepository> { IosAuthRepository() }
}

class IosAuthRepository : IAuthRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        // iOS Phone Auth usually requires APNs setup and verifyPhoneNumber
        // For now, leaving as TODO or you can implement APNs later
        return Result.failure(Exception("Phone Auth requires APNs setup on iOS"))
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        return Result.failure(Exception("Phone Auth requires APNs setup on iOS"))
    }

    override suspend fun signInWithGoogle(idToken: String): Result<Unit> {
        return try {
            // Create credentials using the ID Token obtained from the Launcher
            val credential = GoogleAuthProvider.credential(idToken, null)

            // Sign in using the GitLive Firebase wrapper
            Firebase.auth.signInWithCredential(credential)

            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun getUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }
}