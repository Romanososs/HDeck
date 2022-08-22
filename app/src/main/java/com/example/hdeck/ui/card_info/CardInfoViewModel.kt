package com.example.hdeck.ui.card_info

import androidx.lifecycle.SavedStateHandle
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.repository.CardRepositoryImpl
import com.example.hdeck.state.CardInfoState
import com.example.hdeck.state.CardInfoStateImpl
import com.example.hdeck.ui.BaseViewModel
import com.example.hdeck.ui.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface CardInfoViewModel : BaseViewModel {
    val state: CardInfoState
}

@HiltViewModel
class CardInfoViewModelImpl @Inject constructor(
    private val savedState: SavedStateHandle,
    private val localeService: LocaleService,
    private val repo: CardRepositoryImpl
) : CardInfoViewModel, BaseViewModelImpl() {
    private val _state = CardInfoStateImpl()

    override val state: CardInfoState
        get() = _state
}