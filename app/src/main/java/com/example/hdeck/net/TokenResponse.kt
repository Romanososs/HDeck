package com.example.hdeck.net

data class TokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val scope: String
)
