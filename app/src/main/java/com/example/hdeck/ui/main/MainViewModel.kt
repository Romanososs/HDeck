package com.example.hdeck.ui.main

import androidx.lifecycle.viewModelScope
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.enums.Category
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.repository.MetadataRepository
import com.example.hdeck.state.IndexedList
import com.example.hdeck.state.MetadataState
import com.example.hdeck.state.MetadataStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainViewModel : BaseViewModel {
    val state: MetadataState
    fun onLocaleClick(locale: String, after:() -> Unit)
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
    private val localeService: LocaleService,
    private val navigator: Navigator
) : MainViewModel, BaseViewModelImpl() {
    private val _state = MetadataStateImpl()
    override val state: MetadataState
        get() = _state

    init {
        viewModelScope.launch {
            //FIXME viewModel collects even when view is hidden
            //https://developer.android.com/kotlin/flow#collect
            localeService.language.collectLatest {
                viewModelScope.launch {
                    _state.heroClassList.value = _state.heroClassList.value?.copy(
                        list = repo.getHeroClassList()
                    )
                }
                viewModelScope.launch {
                    _state.cardSetList.value = _state.cardSetList.value?.copy(
                        list = repo.getCardSetList()
                    )
                }
                viewModelScope.launch {
                    _state.cardRarityList.value = _state.cardRarityList.value?.copy(
                        list = repo.getCardRarityList()
                    )
                }
            }
        }
    }

    override fun onLocaleClick(locale: String, after: () -> Unit) {
        viewModelScope.launch {
            localeService.setLocale(locale)
            after()
        }
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
        _state.activeCategory.value = Category.HeroClass
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
        _state.activeCategory.value = Category.CardSet
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

    private fun navigateToCardRarityList() {
        _state.activeCategory.value = Category.CardRarity
        navigator.navigateToCardRarityList(state.cardRarityList.value?.getCurrent()?.slug)
    }

}