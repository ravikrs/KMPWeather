package com.ravikrs.kmp.weather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform