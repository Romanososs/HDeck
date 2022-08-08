package com.example.hdeck.data_source

import com.example.hdeck.model.HeroClass
import com.example.hdeck.net.RetrofitApi
import com.example.hdeck.net.TokenResponse
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface RetrofitDataSource{
    suspend fun getToken(): TokenResponse
    suspend fun getHeroClassList(locale: String, accessToken: String): List<HeroClass>
}
class RetrofitDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
): RetrofitDataSource {
    private val BASE_URL = "https://eu.api.blizzard.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    override suspend fun getToken(): TokenResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getHeroClassList(locale: String, accessToken: String): List<HeroClass> {
        return retrofit.getHeroClassList("classes", locale, accessToken)
    }

}