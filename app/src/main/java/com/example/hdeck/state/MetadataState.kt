package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hdeck.model.CardRarity
import com.example.hdeck.model.CardSet
import com.example.hdeck.model.HeroClass

interface MetadataState {
    val cardSetList: LiveData<IndexedList<CardSet>>
    val cardRarityList: LiveData<IndexedList<CardRarity>>
    val heroClassList: LiveData<IndexedList<HeroClass>>
}

class MetadataStateImpl : MetadataState {
    override val cardSetList: MutableLiveData<IndexedList<CardSet>> = MutableLiveData()
    override val cardRarityList: MutableLiveData<IndexedList<CardRarity>> = MutableLiveData()
    override val heroClassList: MutableLiveData<IndexedList<HeroClass>> = MutableLiveData()
}

data class IndexedList<T>(
    val list: List<T>,
    val index: Int = 0
) {
    fun isNotEmpty() = list.isNotEmpty()
    fun getCurrent(): T? = if(list.size > index) list[index] else null
}