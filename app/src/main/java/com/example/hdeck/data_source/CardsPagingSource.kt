package com.example.hdeck.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hdeck.auth.AuthService
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.Card
import com.example.hdeck.model.Cards
import com.example.hdeck.model.enums.Category
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardsPagingSource @AssistedInject constructor(
    private val authService: AuthService,
    private val localeService: LocaleService,
    @Assisted("category") val category: Category,
    @Assisted("slug") val slug: String
) : PagingSource<Int, Card>() {
    private val BASE_URL = "https://eu.api.blizzard.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitApi::class.java)

    override fun getRefreshKey(state: PagingState<Int, Card>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Card> {
        val pageNumber = params.key ?: 1
        val response = getCards(pageNumber, params.loadSize)
        return LoadResult.Page(
            data = response.cards,
            prevKey = if (pageNumber > 1) pageNumber - 1 else null,
            nextKey = if (response.pageCount == pageNumber) null else pageNumber + 1
        )
    }

    private suspend fun getCards(page: Int, pageSize: Int): Cards {
        val accessToken = authService.getToken()
        val locale = localeService.getLocale()
        return when (category) {
            Category.CardSet -> retrofit.getCardList(
                locale,
                page,
                pageSize,
                accessToken, set = slug
            )
            Category.HeroClass -> retrofit.getCardList(
                locale,
                page,
                pageSize,
                accessToken, heroClass = slug
            )
            Category.CardRarity -> retrofit.getCardList(
                locale,
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