package com.adoyo.dictionaryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adoyo.common.Resource
import com.adoyo.common.UiEvents
import com.adoyo.dictionaryapp.data.repository.DefinitionRepository
import com.adoyo.dictionaryapp.presentation.uistate.DefinitionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    fun getDefinition() {
        _definitionUiState.value = definitionUiState.value.copy(
            isLoading = true
        )

        val word = _typedWord.value

        if (word.isNotEmpty()) {
            viewModelScope.launch {
                definitionRepository.getDefinition(word = word).collect { response ->
                    when (response) {
                        is Resource.Error -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = emptyList()
                            )
                            _eventFlow.emit(
                                UiEvents.SnackBarEvent(
                                    message = response.message ?: "Something went wrong"
                                )
                            )
                        }
                        is Resource.Success -> {
                            _definitionUiState.value = definitionUiState.value.copy(
                                isLoading = false,
                                definition = response.data
                            )
                        }
                        else -> {
                            definitionUiState
                        }
                    }
                }
            }
        } else {
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        viewModelScope.launch {
            _definitionUiState.value = definitionUiState.value.copy(
                isLoading = false
            )
            _eventFlow.emit(
                UiEvents.SnackBarEvent(
                    message = "Please enter a word"
                )
            )
        }
    }
}