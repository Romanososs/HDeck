package com.example.hdeck.model

data class Cards(
    val cards: MutableList<CardApi>,
    val cardCount: Int,
    val pageCount: Int,
    val page: Int
)
