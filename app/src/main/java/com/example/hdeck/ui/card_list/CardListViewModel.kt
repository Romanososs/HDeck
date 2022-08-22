package com.example.hdeck.ui.card_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.hdeck.data_source.CardsPagingSource
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.enums.Category
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.state.CardListState
import com.example.hdeck.state.CardListStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface CardListViewModel: BaseViewModel {
    val state: CardListState
    fun onItemClick(slug: String?)
}

@HiltViewModel
class CardListViewModelImpl @Inject constructor(
    localeService: LocaleService,
    private val savedState: SavedStateHandle,
    private val factory: CardsPagingSource.Factory,
    private val navigator: Navigator
) : CardListViewModel, BaseViewModelImpl(localeService) {
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
            factory.create(Category.values()[categoryId], slug).also { currentPagingSource = it }
        }.liveData.cachedIn(viewModelScope)
    )

    override val state: CardListState
        get() = _state

    override fun onItemClick(slug: String?) {
        navigator.navigateToCard(slug)
    }

    override fun fetchData() {
        currentPagingSource?.invalidate()
    }
}