package com.example.hdeck.auth

import com.example.hdeck.data_source.AuthDataSource
import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.data_source.StoreDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*
import javax.inject.Inject

interface AuthService {
    suspend fun getToken(): String
}

class AuthServiceImpl @Inject constructor(
    private val store: StoreDataSource,
    private val dataSource: AuthDataSource
) : AuthService {
    private var authDate: AuthData? = null
    private val mutex = Mutex()

    override suspend fun getToken(): String {
        mutex.withLock {
            authDate = store.authData.first()
            if (authDate != null && Date(authDate!!.expires_in) > Date()) return authDate!!.access_token
            return dataSource.getToken().also {
                store.saveToken(
                    it.access_token,
                    Date().time + it.expires_in * 1000
                )
            }.access_token
        }
    }
}