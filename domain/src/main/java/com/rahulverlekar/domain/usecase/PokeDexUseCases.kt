package com.rahulverlekar.domain.usecase

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface PokeDexUseCases {

    suspend fun getAllPokemon(): String
}

@Module
@InstallIn(ViewModelComponent::class)
class PokeDexUseCasesImpl @Inject constructor(val remote: PokeDexUseCases): PokeDexUseCases{

    override suspend fun getAllPokemon(): String {
        return remote.getAllPokemon()
    }
}
