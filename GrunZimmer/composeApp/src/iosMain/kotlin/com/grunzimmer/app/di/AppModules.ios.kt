package com.grunzimmer.app.di

import com.grunzimmer.app.domain.repository.IAuthRepository
import org.koin.dsl.module

actual val platformModule = module {
    single<IAuthRepository> { IosAuthRepository() }
}

class IosAuthRepository : IAuthRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        // TODO: Implement iOS Phone Auth
        return Result.failure(Exception("Not implemented on iOS yet"))
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        return Result.failure(Exception("Not implemented on iOS yet"))
    }

    override fun isUserLoggedIn(): Boolean = false
    override fun getUserId(): String? = null
}