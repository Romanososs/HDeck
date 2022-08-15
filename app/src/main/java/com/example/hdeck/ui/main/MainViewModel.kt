package com.example.hdeck.ui.main

import androidx.lifecycle.viewModelScope
import com.example.hdeck.R
import com.example.hdeck.model.Category
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.state.IndexedList
import com.example.hdeck.state.MetadataState
import com.example.hdeck.state.MetadataStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.placeholder.PlaceholderFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MainViewModel {
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
    private val repo: MetadataRepository,
    private val navigator: Navigator
) : MainViewModel, BaseViewModel() {
    private val _state = MetadataStateImpl()
    override val state: MetadataState
        get() = _state

    init {
        jobs.add(viewModelScope.launch {
            val list = repo.getHeroClassList().toMutableList()
            list.removeIf { it.cardId == 0 }
            _state.heroClassList.value = IndexedList(list)

        })
        jobs.add(viewModelScope.launch {
            _state.cardSetList.value = IndexedList(repo.getCardSetList())

        })
        jobs.add(viewModelScope.launch {
            _state.cardRarityList.value = IndexedList(repo.getCardRarityList())
        })
    }

    override fun onClassHeroClick() {
        navigateToHeroClassList()
    }

    override fun onClassHeroListItemClick(index: Int) {
        _state.heroClassList.value = _state.heroClassList.value?.copy(
            index = index
        )
        navigateToHeroClassList()
    }

    private fun navigateToHeroClassList() {
        navigator.navigateToHeroClassList(state.heroClassList.value?.getCurrent()?.slug)
    }

    override fun onCardSetClick() {
        navigateToCardSetList()
    }

    override fun onCardSetListItemClick(index: Int) {
        _state.cardSetList.value = _state.cardSetList.value?.copy(
            index = index
        )
        navigateToCardSetList()
    }

    private fun navigateToCardSetList() {
        navigator.navigateToCardSetList(state.cardSetList.value?.getCurrent()?.slug)
    }

    override fun onCardRarityClick() {
        navigateToCardRarityList()
    }

    override fun onCardRarityListItemClick(index: Int) {
        _state.cardRarityList.value = _state.cardRarityList.value?.copy(
            index = index
        )
        navigateToCardRarityList()
    }
    private fun navigateToCardRarityList(){
        navigator.navigateToCardRarityList(state.cardSetList.value?.getCurrent()?.slug)
    }

}