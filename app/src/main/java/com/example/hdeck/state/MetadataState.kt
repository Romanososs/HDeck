package com.example.hdeck.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hdeck.model.CardRarity
import com.example.hdeck.model.CardSet
import com.example.hdeck.model.HeroClass
import com.example.hdeck.model.enums.Category

interface MetadataState {
    val activeCategory: LiveData<Category>
    val cardSetList: LiveData<IndexedList<CardSet>>
    val cardRarityList: LiveData<IndexedList<CardRarity>>
    val heroClassList: LiveData<IndexedList<HeroClass>>
}

class MetadataStateImpl : MetadataState {
    override val activeCategory: MutableLiveData<Category> = MutableLiveData()
    override val cardSetList: MutableLiveData<IndexedList<CardSet>> = MutableLiveData(IndexedList())
    override val cardRarityList: MutableLiveData<IndexedList<CardRarity>> = MutableLiveData(IndexedList())
    override val heroClassList: MutableLiveData<IndexedList<HeroClass>> = MutableLiveData(IndexedList())
}

data class IndexedList<T>(
    val list: List<T> = emptyList(),
    val index: Int = 0
) {
    fun isNotEmpty() = list.isNotEmpty()
    fun getCurrent(): T? = if(list.size > index) list[index] else null
}