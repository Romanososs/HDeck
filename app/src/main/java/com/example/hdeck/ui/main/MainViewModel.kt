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
    fun onClassHeroClick()
    fun onClassHeroListItemClick(index: Int)
    fun onCardSetClick()
    fun onCardSetListItemClick(index: Int)
    fun onCardRarityClick()
    fun onCardRarityListItemClick(index: Int)
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
        jobs.add(viewModelScope.launch{
            _state.cardSetList.value = IndexedList(repo.getCardSetList())

        })
        jobs.add(viewModelScope.launch{
            _state.cardRarityList.value = IndexedList(repo.getCardRarityList())
        })
    }

    override fun onClassHeroClick() {
        //navigation
    }

    override fun onClassHeroListItemClick(index: Int) {
        _state.heroClassList.value = _state.heroClassList.value?.copy(
            index = index
        )
        //navigation
    }

    override fun onCardSetClick() {
        //navigation
    }

    override fun onCardSetListItemClick(index: Int) {
        _state.cardSetList.value = _state.cardSetList.value?.copy(
            index = index
        )
        //navigation
    }

    override fun onCardRarityClick() {
        //navigation
    }

    override fun onCardRarityListItemClick(index: Int) {
        _state.cardRarityList.value = _state.cardRarityList.value?.copy(
            index = index
        )
        //navigation
    }

}