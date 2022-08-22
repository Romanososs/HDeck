package com.example.hdeck.data_source

import com.example.hdeck.model.metadata.Metadata
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RetrofitDataSource {
    suspend fun getMetadata(locale: String, accessToken: String): Metadata
}

class RetrofitDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val retrofit: RetrofitApi
) : RetrofitDataSource {
    override suspend fun getMetadata(locale: String, accessToken: String): Metadata {
        return withContext(dispatcher) { retrofit.getMetadata(locale, accessToken) }
    }
}