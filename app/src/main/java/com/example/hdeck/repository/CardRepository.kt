package com.example.hdeck.repository

import androidx.paging.*
import com.example.hdeck.auth.AuthService
import com.example.hdeck.data_source.CardsPagingSource
import com.example.hdeck.model.Card
import com.example.hdeck.model.Category
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardsPagingSourceFactory: CardsPagingSource.Factory
) {
    fun getCardsSource(category: Category, slug: String): PagingSource<Int, Card> {
        return cardsPagingSourceFactory.create(category, slug)
    }
}