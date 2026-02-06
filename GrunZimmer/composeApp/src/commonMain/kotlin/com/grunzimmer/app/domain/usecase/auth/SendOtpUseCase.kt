package com.grunzimmer.app.domain.usecase.auth

import com.grunzimmer.app.domain.repository.IAuthRepository

class SendOtpUseCase(private val repository: IAuthRepository) {
    suspend operator fun invoke(phoneNumber: String): Result<Unit> {
        return repository.sendOtp(phoneNumber)
    }
}