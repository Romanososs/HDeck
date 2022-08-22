package com.example.hdeck.data_source

import com.example.hdeck.model.metadata.Metadata
import com.example.hdeck.auth.TokenResponse
import com.example.hdeck.model.*
import retrofit2.http.*

interface RetrofitApi {
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String = "client_credentials"
    ): TokenResponse

    @GET("hearthstone/metadata")
    suspend fun getMetadata(
        @Query("locale") locale: String,
        @Query("access_token") access_token: String
    ): Metadata


    @GET("hearthstone/cards")
    suspend fun getCardList(
        @Query("locale") locale: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("access_token") access_token: String,
        @Query("set") set: String? = null,
        @Query("class") heroClass: String? = null,
        @Query("rarity") rarity: String? = null,
    ): Cards
}