package com.example.hdeck.ui.card_list

import androidx.lifecycle.*
import androidx.paging.*
import com.example.hdeck.data_source.CardsPagingSource
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.enums.Category
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.state.CardListState
import com.example.hdeck.state.CardListStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CardListViewModel: BaseViewModel {
    val state: CardListState
    fun onItemClick(slug: String?)
}

@HiltViewModel
class CardListViewModelImpl @Inject constructor(
    private val savedState: SavedStateHandle,
    private val localeService: LocaleService,
    private val cardsPagingSourceFactory: CardsPagingSource.Factory,
    private val navigator: Navigator
) : CardListViewModel, BaseViewModelImpl() {
    private val categoryId = savedState.get<Int>("categoryId") ?: 0
    private val slug = savedState.get<String>("slug") ?: ""
    private var currentPagingSource: PagingSource<Int, CardApi>? = null
    private val _state = CardListStateImpl(
        Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                maxSize = 200
            )
        ) {
            cardsPagingSourceFactory.create(Category.values()[categoryId], slug).also { currentPagingSource = it }
        }.liveData.cachedIn(viewModelScope)
    )

    override val state: CardListState
        get() = _state

    override fun onItemClick(slug: String?) {
        navigator.navigateToCard(slug)
    }

    init{
        viewModelScope.launch {
            localeService.language.collectLatest {
                currentPagingSource?.invalidate()
            }
        }
    }
}