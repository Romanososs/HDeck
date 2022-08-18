package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.hdeck.model.Card

interface DeckListState {
    val cardList: LiveData<PagingData<Card>>
}

class DeckListStateImpl(override val cardList: LiveData<PagingData<Card>>) : DeckListState {
//    override val cardList: MutableLiveData<PagingData<Card>>
}