package com.example.hdeck.data_source

import com.example.hdeck.BuildConfig
import com.example.hdeck.auth.TokenResponse
import com.example.hdeck.di.AuthRetrofit
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AuthDataSource {
    suspend fun getToken(): TokenResponse
}

class AuthDataSourceImpl(
    private val dispatcher: CoroutineDispatcher,
    private val retrofit: RetrofitApi
) : AuthDataSource {

    override suspend fun getToken(): TokenResponse {
        return withContext(dispatcher) {
            retrofit.getToken(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET
            )
        }
    }

}