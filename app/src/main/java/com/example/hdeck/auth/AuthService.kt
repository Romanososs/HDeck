package com.example.hdeck.auth

import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.data_source.StoreDataSource
import javax.inject.Inject

interface AuthService {
    suspend fun getToken(): String
}

class AuthServiceImpl @Inject constructor(
    private val store: StoreDataSource,
    private val dataSource: RetrofitDataSource
): AuthService {

    private val authDate: AuthData? = null
    override suspend fun getToken(): String {
        if (authDate != null &&)
        return "US5QxzxG9V3BxfYWgSbMYX6veo7LDoy65C"
    }

}