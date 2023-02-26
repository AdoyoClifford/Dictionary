package com.adoyo.dictionaryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.adoyo.common.UiEvents
import com.adoyo.dictionaryapp.data.repository.DefinitionRepository
import com.adoyo.dictionaryapp.presentation.uistate.DefinitionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DefinitionViewModel @Inject constructor(
    private val definitionRepository: DefinitionRepository
) : ViewModel() {

    private val _definitionUiState = MutableStateFlow(DefinitionUiState())
    val definitionUiState: StateFlow<DefinitionUiState> = _definitionUiState.asStateFlow()

    private val _typedWord = mutableStateOf("")
    val typedWord: State<String> = _typedWord

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()
}