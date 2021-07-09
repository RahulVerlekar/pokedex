package com.rahulverlekar.pokedex.di

import com.rahulverlekar.data.local.room.repository.PokeDexDB
import com.rahulverlekar.data.network.repository.PokeDexNetworkRepo
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.domain.usecase.PokeDexUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun providePokemonUseCases(network: PokeDexNetworkRepo, local: PokeDexDB): PokeDexUseCases = PokeDexUseCasesImpl(network, local)
}