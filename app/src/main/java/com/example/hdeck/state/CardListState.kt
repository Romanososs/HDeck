package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.hdeck.model.CardApi
import com.example.hdeck.model.Filter

interface CardListState {
    val filter: LiveData<Filter>
}

class CardListStateImpl(
//    override val cardApiList: LiveData<PagingData<CardApi>>
) : CardListState {
    override val filter: MutableLiveData<Filter> = MutableLiveData(Filter())
}