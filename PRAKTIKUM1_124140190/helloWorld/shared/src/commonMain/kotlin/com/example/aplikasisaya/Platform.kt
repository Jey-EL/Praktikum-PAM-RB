package com.example.aplikasisaya

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform