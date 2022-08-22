package com.example.hdeck.ui.card_info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.model.Card
import com.example.hdeck.service.CardsService
import com.example.hdeck.service.MetadataService
import com.example.hdeck.state.CardInfoState
import com.example.hdeck.state.CardInfoStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CardInfoViewModel : BaseViewModel {
    val state: CardInfoState
}

@HiltViewModel
class CardInfoViewModelImpl @Inject constructor(
    localeService: LocaleService,
    private val savedState: SavedStateHandle,
    private val metadata: MetadataService,
    private val cards: CardsService
) : CardInfoViewModel, BaseViewModelImpl(localeService) {

    private val slug = savedState.get<String>("slug") ?: ""
    private val _state = CardInfoStateImpl()

    override val state: CardInfoState
        get() = _state

    override fun fetchData() {
        viewModelScope.launch {
            val apiCard = cards.getCard(slug)
            _state.card.value = Card(
                card = apiCard,
                heroClass = metadata.getHeroClass(apiCard.classId).name,
                type = metadata.getType(apiCard.cardTypeId).name,
                set = metadata.getCardSet(apiCard.cardSetId).name,
                rarity = metadata.getRarity(apiCard.rarityId).name
            )
        }
    }
}