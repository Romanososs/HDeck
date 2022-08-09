package com.example.hdeck.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {
    val jobs = mutableListOf<Job?>()
    fun onViewShown() {}
    fun onViewHidden() {}
    override fun onCleared() {
        super.onCleared()
        jobs.forEach {
            it?.cancel()
        }
    }
}