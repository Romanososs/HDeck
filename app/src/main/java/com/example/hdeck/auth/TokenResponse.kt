package com.example.hdeck.auth

data class TokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val scope: String
)
