package com.adoyo.di

import com.adoyo.dictionaryapp.data.remote.DictionaryApi
import com.adoyo.dictionaryapp.data.repository.DefinitionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDefinitionRepository(
        dictionaryApi: DictionaryApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DefinitionRepository {
        return DefinitionRepositoryImpl(dictionaryApi,ioDispatcher)
    }
}