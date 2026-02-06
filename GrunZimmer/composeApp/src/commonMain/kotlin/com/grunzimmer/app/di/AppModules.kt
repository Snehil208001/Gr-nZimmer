package com.grunzimmer.app.di

import com.grunzimmer.app.domain.usecase.auth.SendOtpUseCase
import com.grunzimmer.app.domain.usecase.auth.VerifyOtpUseCase
import com.grunzimmer.app.mainui.auth.viewmodel.AuthViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// 1. Define the Common Module (Use Cases & ViewModels)
val appModule = module {
    factory { SendOtpUseCase(get()) }
    factory { VerifyOtpUseCase(get()) }
    factory { AuthViewModel(get(), get()) }
}

// 2. Expect a Platform Module (For the Repository)
expect val platformModule: Module