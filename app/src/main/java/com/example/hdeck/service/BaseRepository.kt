package com.example.hdeck.service

import com.example.hdeck.state.BaseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface BaseRepository {
    suspend fun <T> apiCall(
        apiCall: suspend () -> T
    ): BaseState<T> {
        return withContext(Dispatchers.IO) {
            try {
                BaseState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        BaseState.Failure(throwable.code(), throwable.message)
                    }
                    else -> {
                        BaseState.Failure(null, null)
                    }
                }
            }
        }
    }
}