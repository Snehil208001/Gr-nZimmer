package com.grunzimmer.app.domain.repository

interface IAuthRepository {
    /**
     * Sends the OTP to the provided phone number.
     * On successful initiation, returns a verification ID (or internal handler).
     */
    suspend fun sendOtp(phoneNumber: String): Result<Unit>

    /**
     * Verifies the OTP code entered by the user.
     * @param code The SMS code entered by the user.
     */
    suspend fun verifyOtp(code: String): Result<Unit>

    /**
     * Checks if a user is currently logged in.
     */
    fun isUserLoggedIn(): Boolean

    fun getUserId(): String?
}