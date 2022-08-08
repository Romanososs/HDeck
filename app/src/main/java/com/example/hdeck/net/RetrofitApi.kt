package com.example.hdeck.net

import com.example.hdeck.model.HeroClass
import retrofit2.http.*

interface RetrofitApi {
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String =  "client_credentials"
    ): TokenResponse

    @GET("hearthstone/metadata/{type}")
    suspend fun getHeroClassList(
        @Path("type") type: String,
        @Query("locale") locale: String,
        @Query("access_token") access_token: String
    ): List<HeroClass>
}