package com.example.hdeck.data_source

import com.example.hdeck.auth.TokenResponse
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
    private val dispatcher: CoroutineDispatcher
) : AuthDataSource {
    private val BASE_URL = "https://us.battle.net"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    override suspend fun getToken(): TokenResponse {
        return withContext(dispatcher) {
            retrofit.getToken(
                "8b571509977049a9971bcf4b206d7dd3",
                "C6rxdUPB0VDurAjxKZS1wys5RpS49vBN"
            )
        }
    }

}