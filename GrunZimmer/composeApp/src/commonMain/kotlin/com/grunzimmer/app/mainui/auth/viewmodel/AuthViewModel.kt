package com.grunzimmer.app.mainui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grunzimmer.app.domain.repository.IAuthRepository
import com.grunzimmer.app.domain.usecase.auth.SendOtpUseCase
import com.grunzimmer.app.domain.usecase.auth.VerifyOtpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isOtpSent: Boolean = false,
    val isVerified: Boolean = false,
    val isGoogleLoginSuccess: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val sendOtpUseCase: SendOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val authRepository: IAuthRepository // Added dependency
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun sendOtp(phoneNumber: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = sendOtpUseCase(phoneNumber)
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isOtpSent = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Failed to send OTP") }
            }
        }
    }

    fun verifyOtp(code: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = verifyOtpUseCase(code)
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isVerified = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Invalid OTP") }
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signInWithGoogle(idToken)
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isGoogleLoginSuccess = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Google Sign-In Failed") }
            }
        }
    }

    fun resetState() {
        _uiState.update { AuthUiState() }
    }
}