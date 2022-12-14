package com.example.hdeck.service

import androidx.paging.PagingSource
import com.example.hdeck.auth.AuthService
import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.Language
import com.example.hdeck.model.enums.Category
import javax.inject.Inject

/**
 * For in memory cache of pages
 */
interface CardsService {
    fun onListChange()
    suspend fun setPage(pageNumber: Int, page: PagingSource.LoadResult.Page<Int, CardApi>)
    suspend fun getPage(pageNumber: Int): PagingSource.LoadResult.Page<Int, CardApi>?
    suspend fun getCard(slug: String): CardApi
}

class CardsServiceImpl @Inject constructor(
    private val dataSource: RetrofitDataSource,
    private val localeService: LocaleService,
    private val authService: AuthService,
) : CardsService {
    private val pages: MutableMap<Int, PagingSource.LoadResult.Page<Int, CardApi>> = mutableMapOf()

    override fun onListChange() {
        pages.clear()
    }

    override suspend fun setPage(
        pageNumber: Int,
        page: PagingSource.LoadResult.Page<Int, CardApi>
    ) {
        if (pages.containsKey(pageNumber))
            pages.replace(pageNumber, page)
        else
            pages[pageNumber] = page
    }

    override suspend fun getPage(pageNumber: Int): PagingSource.LoadResult.Page<Int, CardApi>? {
        return pages.getOrDefault(pageNumber, null)
    }

    override suspend fun getCard(slug: String): CardApi {
        pages.forEach { page ->
            val card = page.value.data.firstOrNull { card ->
                card.slug == slug
            }
            if (card != null) return card
        }
        return dataSource.getCard(slug, localeService.getApiLocale(), authService.getToken())
    }
}