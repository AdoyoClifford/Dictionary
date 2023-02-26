package com.adoyo.dictionaryapp.presentation.uistate

import com.adoyo.dictionaryapp.data.remote.DefinitionResponseModel

data class DefinitionUiState(
    val definition: List<DefinitionResponseModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
