package com.example.hdeck.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hdeck.auth.AuthService
import com.example.hdeck.di.BaseRetrofit
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.Card
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.Cards
import com.example.hdeck.model.Language
import com.example.hdeck.model.enums.Category
import com.example.hdeck.service.CardsService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject



class CardsPagingSource @AssistedInject constructor(
    private val authService: AuthService,
    private val localeService: LocaleService,
    private val cardsService: CardsService,
    @BaseRetrofit private val retrofit: RetrofitApi,
    @Assisted("category") val category: Category,
    @Assisted("slug") val slug: String
) : PagingSource<Int, CardApi>() {

    override fun getRefreshKey(state: PagingState<Int, CardApi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CardApi> {
        val pageNumber = params.key ?: 1
        val response = getCards(pageNumber, params.loadSize)
        val data = response.cards
        data.removeIf { it.copyOfCardId != 0 }

        return LoadResult.Page(
            data = data,
            prevKey = if (pageNumber > 1) pageNumber - 1 else null,
            nextKey = if (response.pageCount == pageNumber) null else pageNumber + 1
        ).also { cardsService.setPage(pageNumber-1, it) }
    }

    private suspend fun getCards(page: Int, pageSize: Int): Cards {
        val accessToken = authService.getToken()
        val locale = localeService.getLanguage().also {
            cardsService.setLanguage(it)
        }
        return when (category) {
            Category.CardSet -> retrofit.getCardList(
                locale.apiCode,
                page,
                pageSize,
                accessToken, set = slug
            )
            Category.HeroClass -> retrofit.getCardList(
                locale.apiCode,
                page,
                pageSize,
                accessToken, heroClass = slug
            )
            Category.CardRarity -> retrofit.getCardList(
                locale.apiCode,
                page,
                pageSize,
                accessToken, rarity = slug
            )
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("category") category: Category,
            @Assisted("slug") slug: String
        ): CardsPagingSource
    }
}