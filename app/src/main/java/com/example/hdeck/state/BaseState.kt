package com.example.hdeck.state

sealed class BaseState<out T> {
    data class Success<out T>(val value: T) : BaseState<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorMessage: String?
    ) : BaseState<Nothing>()
    object Loading : BaseState<Nothing>()
}

