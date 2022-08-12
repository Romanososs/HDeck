package com.example.hdeck.data_source

import com.example.hdeck.auth.TokenResponse
import com.example.hdeck.model.CardRarity
import com.example.hdeck.model.CardSet
import com.example.hdeck.model.HeroClass
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String =  "client_credentials"
    ): TokenResponse

//TODO maybe smash it into one function
    @GET("hearthstone/metadata/classes")
    suspend fun getHeroClassList(
        @Query("locale") locale: String,
        @Query("access_token") access_token: String
    ): List<HeroClass>

    @GET("hearthstone/metadata/sets")
    suspend fun getCardSetList(
        @Query("locale") locale: String,
        @Query("access_token") access_token: String
    ): List<CardSet>

    @GET("hearthstone/metadata/rarities")
    suspend fun getCardRarityList(
        @Query("locale") locale: String,
        @Query("access_token") access_token: String
    ): List<CardRarity>
}