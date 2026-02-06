package com.grunzimmer.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        // Load Common Module AND Platform Module
        modules(appModule, platformModule)
    }
}