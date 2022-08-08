package com.example.hdeck.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.state.BaseState
import com.example.hdeck.state.MetadataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MainViewModel {
    val state: LiveData<BaseState<MetadataState>>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repo: MetadataRepository
): MainViewModel, ViewModel() {
    private val _state = MutableLiveData<BaseState<MetadataState>>(BaseState.Loading)
    override val state: LiveData<BaseState<MetadataState>> = _state

    init{
        viewModelScope.launch{
            val data = repo.getHeroClassList().toMutableList().removeIf {
                it.cardId == 0
            }
            println("TAGTAG "+ data)
        }
    }
}