package com.adoyo.dictionaryapp.data.repository

import com.adoyo.common.Resource
import com.adoyo.dictionaryapp.data.remote.DefinitionResponseModel
import kotlinx.coroutines.flow.Flow

interface DefinitionRepository {
    suspend fun getDefinition(word: String): Flow<Resource<List<DefinitionResponseModel>>>
}