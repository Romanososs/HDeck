package com.example.hdeck.service

import androidx.paging.PagingSource
import com.example.hdeck.auth.AuthService
import com.example.hdeck.data_source.RetrofitDataSource
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.Language
import javax.inject.Inject

interface CardsService {
    suspend fun setLanguage(language: Language)
    suspend fun setPage(index: Int, page: PagingSource.LoadResult.Page<Int, CardApi>)
    suspend fun getCard(slug: String): CardApi
}

class CardsServiceImpl @Inject constructor(
    private val dataSource: RetrofitDataSource,
    private val localeService: LocaleService,
    private val authService: AuthService,
) : CardsService {
    private val pages: MutableList<PagingSource.LoadResult.Page<Int, CardApi>> = mutableListOf()
    private var currentLocale: Language? = null

    override suspend fun setLanguage(language: Language) {
        if (currentLocale != language) {
            pages.clear()
            currentLocale = language
        }
    }

    override suspend fun setPage(index: Int, page: PagingSource.LoadResult.Page<Int, CardApi>) {
        while (pages.size < index + 1)
            pages.add(PagingSource.LoadResult.Page(data = emptyList(), null, null))
        pages[index] = page
    }

    override suspend fun getCard(slug: String): CardApi {
        pages.forEach { page ->
            return page.data.first { card ->
                card.slug == slug
            }
        }
        return dataSource.getCard(slug, localeService.getApiLocale(), authService.getToken())
    }
}