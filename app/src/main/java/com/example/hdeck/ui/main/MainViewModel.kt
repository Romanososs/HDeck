package com.example.hdeck.ui.main

import androidx.lifecycle.viewModelScope
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.state.IndexedList
import com.example.hdeck.state.MetadataState
import com.example.hdeck.state.MetadataStateImpl
import com.example.hdeck.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MainViewModel  {
    val state: MetadataState
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repo: MetadataRepository
): MainViewModel, BaseViewModel() {
    private val _state = MetadataStateImpl()
    override val state: MetadataState
        get() = _state

    init{
        jobs.add(viewModelScope.launch{
            val list = repo.getHeroClassList().toMutableList()
            list.removeIf { it.cardId == 0 }
            _state.heroClassList.value = IndexedList(list)

        })
//        jobs.add(viewModelScope.launch{
//            repo.getHeroClassList().toMutableList().removeIf {
//                it.cardId == 0
//            }
//        })
//        jobs.add(viewModelScope.launch{
//            repo.getHeroClassList().toMutableList().removeIf {
//                it.cardId == 0
//            }
//        })
    }
}