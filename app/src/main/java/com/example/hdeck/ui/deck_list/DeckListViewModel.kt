package com.example.hdeck.ui.deck_list

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.hdeck.model.enums.Category
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.state.DeckListState
import com.example.hdeck.state.DeckListStateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface DeckListViewModel {
    val state: DeckListState
}

@HiltViewModel
class DeckListViewModelImpl @Inject constructor(
    private val savedState: SavedStateHandle,
    private val cardRepo: CardRepositoryImpl
) : DeckListViewModel, ViewModel() {
    private val categoryId = savedState.get<Int>("categoryId") ?: 0
    private val slug = savedState.get<String>("slug") ?: ""

    private val _state = DeckListStateImpl(
        Pager(
            PagingConfig(pageSize = 20)
        ) {
            cardRepo.getCardsSource(Category.values()[categoryId], slug)
        }.liveData.cachedIn(viewModelScope)
    )
    override val state: DeckListState
        get() = _state

}