package com.example.hdeck.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

interface BaseViewModel{
    fun onViewShown()
    fun onViewHidden()
}
abstract class BaseViewModelImpl: BaseViewModel, ViewModel() {
//    val jobs = mutableListOf<Job?>()
    override fun onViewShown() {}
    override fun onViewHidden() {}
    override fun onCleared() {
        super.onCleared()
    }
}