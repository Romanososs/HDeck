package com.example.hdeck.ui.deck_list

import androidx.lifecycle.*
import com.example.hdeck.state.BaseState
import com.example.hdeck.state.MetadataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DeckListViewModel {

}

@HiltViewModel
class DeckListViewModelImpl@Inject constructor(
    private val savedState: SavedStateHandle
) : DeckListViewModel, ViewModel() {

    private val _state = MutableLiveData<BaseState<MetadataState>>(BaseState.Loading)
    val state: LiveData<BaseState<MetadataState>> = _state

    init{
        viewModelScope.launch{

        }
    }
}