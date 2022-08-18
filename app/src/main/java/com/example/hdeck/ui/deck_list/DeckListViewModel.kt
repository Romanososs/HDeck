package com.example.hdeck.ui.deck_list

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.hdeck.data_source.StoreDataSource
import com.example.hdeck.model.enums.Category
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.state.DeckListState
import com.example.hdeck.state.DeckListStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

interface DeckListViewModel: BaseViewModel {
    val state: DeckListState
}

@HiltViewModel
class DeckListViewModelImpl @Inject constructor(
    private val savedState: SavedStateHandle,
    private val cardRepo: CardRepositoryImpl
) : DeckListViewModel, BaseViewModelImpl() {
    private val categoryId = savedState.get<Int>("categoryId") ?: 0
    private val slug = savedState.get<String>("slug") ?: ""

    private val _state = DeckListStateImpl(
        Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 40,
                maxSize = 200
            )
        ) {
            cardRepo.getCardsSource(Category.values()[categoryId], slug)
        }.liveData.cachedIn(viewModelScope)
    )
    override val state: DeckListState
        get() = _state
}