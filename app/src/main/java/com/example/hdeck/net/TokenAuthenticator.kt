package com.example.hdeck.net

import com.example.hdeck.data_source.StoreDataSource
import com.example.hdeck.repository.BaseRepository
import com.example.hdeck.state.BaseState
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

//class TokenAuthenticator @Inject constructor(
//    private val store: StoreDataSource,
//    private val api: RetrofitApi
//) : Authenticator, BaseRepository {
//
//    override fun authenticate(route: Route?, response: Response): Request? {
//        return runBlocking {
//            when (val tokenResponse = getUpdatedToken()) {
//                is BaseState.Success<*> -> {
//                    store.saveAccessTokens(
//                        tokenResponse.value.access_token!!,
//                        tokenResponse.value.refresh_token!!
//                    )
//                    response.request.newBuilder()
//                        .header("Authorization", "Bearer ${tokenResponse.value.access_token}")
//                        .build()
//                }
//                else -> null
//            }
//        }
//    }
//
//    private suspend fun getUpdatedToken(): BaseState<TokenResponse> {
//        val refreshToken = store.refreshToken.first()
//        return safeApiCall { tokenApi.refreshAccessToken(refreshToken) }
//    }
//
//}