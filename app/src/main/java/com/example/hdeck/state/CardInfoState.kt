package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hdeck.model.Card

interface CardInfoState {
    val card: LiveData<Card>
}

class CardInfoStateImpl : CardInfoState {
    override val card: MutableLiveData<Card> = MutableLiveData()
}