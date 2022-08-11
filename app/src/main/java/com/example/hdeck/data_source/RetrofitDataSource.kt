package com.example.hdeck.data_source

import com.example.hdeck.model.CardRarity
import com.example.hdeck.model.CardSet
import com.example.hdeck.model.HeroClass
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface RetrofitDataSource {

    suspend fun getHeroClassList(locale: String, accessToken: String): List<HeroClass>
    suspend fun getCardSetList(locale: String, accessToken: String): List<CardSet>
    suspend fun getCardRarityList(locale: String, accessToken: String): List<CardRarity>
}

class RetrofitDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : RetrofitDataSource {
    private val BASE_URL = "https://eu.api.blizzard.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    override suspend fun getHeroClassList(locale: String, accessToken: String): List<HeroClass> {
        return withContext(dispatcher) { retrofit.getHeroClassList(locale, accessToken) }
    }

    override suspend fun getCardSetList(locale: String, accessToken: String): List<CardSet> {
        return withContext(dispatcher) { retrofit.getCardSetList(locale, accessToken) }
    }

    override suspend fun getCardRarityList(locale: String, accessToken: String): List<CardRarity> {
        return withContext(dispatcher) { retrofit.getCardRarityList(locale, accessToken) }
    }

}