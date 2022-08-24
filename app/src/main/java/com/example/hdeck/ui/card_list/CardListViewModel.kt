package com.example.hdeck.ui.card_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.hdeck.data_source.CardsPagingSource
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.Filter
import com.example.hdeck.model.enums.Category
import com.example.hdeck.model.enums.CategoryFilter
import com.example.hdeck.model.enums.OrderFilter
import com.example.hdeck.navigation.Navigator
import com.example.hdeck.service.CardsService
import com.example.hdeck.state.CardListState
import com.example.hdeck.state.CardListStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface CardListViewModel : BaseViewModel {
    val state: CardListState
    val cardApiList: LiveData<PagingData<CardApi>>
    fun onCategoryFilterClick(filter: CategoryFilter?)
    fun onOrderFilterClick(filter: OrderFilter?)
    fun onItemClick(slug: String?)
}

@HiltViewModel
class CardListViewModelImpl @Inject constructor(
    localeService: LocaleService,
    private val savedState: SavedStateHandle,
    private val cardService: CardsService,
    private val factory: CardsPagingSource.Factory,
    private val navigator: Navigator
) : CardListViewModel, BaseViewModelImpl(localeService) {
    private val categoryId = savedState.get<Int>("categoryId") ?: 0
    private val slug = savedState.get<String>("slug") ?: ""
    private var currentPagingSource: PagingSource<Int, CardApi>? = null

    private val _state = CardListStateImpl()

    override val state: CardListState
        get() = _state
    override val cardApiList: LiveData<PagingData<CardApi>> =
        Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                maxSize = 200
            )
        ) {
            factory.create(Category.values()[categoryId], slug, state.filter.value?.getFilter())
                .also { currentPagingSource = it }
        }.liveData.cachedIn(viewModelScope)

    override fun onCategoryFilterClick(filter: CategoryFilter?) {
        if (filter != null) {
            _state.filter.value = _state.filter.value?.copy(
                category = filter
            )
            cardService.onListChange()
            currentPagingSource?.invalidate()
        }
    }

    override fun onOrderFilterClick(filter: OrderFilter?) {
        if (filter != null) {
            _state.filter.value = _state.filter.value?.copy(
                order = filter
            )
            cardService.onListChange()
            currentPagingSource?.invalidate()
        }
    }

    override fun onItemClick(slug: String?) {
        navigator.navigateToCard(slug)
    }

    override fun fetchData() {
        currentPagingSource?.invalidate()
    }
}