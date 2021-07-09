package com.rahulverlekar.domain.usecase

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface PokeDexUseCases {

    suspend fun getPokemonNames(offset: Int, limit: Int = 20): List<String>
}

@Module
@InstallIn(ViewModelComponent::class)
class PokeDexUseCasesImpl @Inject constructor(private val remote: PokeDexUseCases): PokeDexUseCases{

    override suspend fun getPokemonNames(offset: Int, limit: Int): List<String> {
        return remote.getPokemonNames(offset, limit)
    }
}
