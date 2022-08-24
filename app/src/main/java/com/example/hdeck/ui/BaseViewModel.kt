package com.example.hdeck.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hdeck.localization.LocaleService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface BaseViewModel {
    fun onViewShown()
    fun onViewHidden()
}

abstract class BaseViewModelImpl(
    protected val localeService: LocaleService
) : BaseViewModel, ViewModel() {
    protected val jobs = mutableListOf<Job?>()
    override fun onViewShown() {
        jobs.add(viewModelScope.launch {
            localeService.language.collectLatest {
                fetchData()
            }
        })
    }

    override fun onViewHidden() {
        jobs.forEach {
            it?.cancel()
        }
    }

    abstract fun fetchData()

}