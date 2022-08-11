package com.example.hdeck.auth

import com.example.hdeck.data_source.AuthDataSource
import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.data_source.StoreDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

interface AuthService {
    fun start()
    suspend fun getToken(): String
}

class AuthServiceImpl @Inject constructor(
    private val store: StoreDataSource,
    private val dataSource: AuthDataSource
) : AuthService {
    private val scope = CoroutineScope(Dispatchers.Main)
    private var authDate: AuthData? = null
//FIXME start not always called earlier when getToken
    override fun start() {
        scope.launch {
            store.authData.collect {
                authDate = it
            }
        }
    }
//
    override suspend fun getToken(): String {
        if (authDate != null && Date(authDate!!.expires_in.plus(10000L)) < Date()) return authDate!!.access_token
        val newToken = dataSource.getToken()
        store.saveToken(newToken.access_token, Date().time + newToken.expires_in * 1000)
        return newToken.access_token
    }

}