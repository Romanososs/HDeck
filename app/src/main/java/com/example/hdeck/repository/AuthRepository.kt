package com.example.hdeck.repository

import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.data_source.StoreDataSource
import javax.inject.Inject

interface AuthRepository {
    suspend fun getToken(): String
}

class AuthRepositoryImpl @Inject constructor(
    private val store: StoreDataSource,
    private val dataSource: RetrofitDataSource
): AuthRepository, BaseRepository {
    override suspend fun getToken(): String {
        return "USy64irXs74zi4yefQCt8pf4eC4xT60m58"
    }

}