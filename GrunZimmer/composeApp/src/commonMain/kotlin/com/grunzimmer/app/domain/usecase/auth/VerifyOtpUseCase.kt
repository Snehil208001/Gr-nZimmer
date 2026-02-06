package com.grunzimmer.app.domain.usecase.auth

import com.grunzimmer.app.domain.repository.IAuthRepository

class VerifyOtpUseCase(private val repository: IAuthRepository) {
    suspend operator fun invoke(code: String): Result<Unit> {
        return repository.verifyOtp(code)
    }
}