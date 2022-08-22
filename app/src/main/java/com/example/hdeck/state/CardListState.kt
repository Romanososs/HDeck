package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.hdeck.model.CardApi

interface CardListState {
    val cardApiList: LiveData<PagingData<CardApi>>
}

class CardListStateImpl(override val cardApiList: LiveData<PagingData<CardApi>>) : CardListState {
//    override val cardList: MutableLiveData<PagingData<Card>>
}