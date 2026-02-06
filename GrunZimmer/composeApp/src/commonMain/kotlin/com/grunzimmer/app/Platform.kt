package com.grunzimmer.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform