package com.rahulverlekar.domain.usecase

import com.rahulverlekar.domain.model.Pokemon
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface PokeDexUseCases {

    suspend fun getPokemonNames(offset: Int, limit: Int = 20): List<String>

    suspend fun getPokemon(name: String): Pokemon

    suspend fun getPokemons(offset: Int, limit: Int = 20): List<Pokemon>
}

@Module
@InstallIn(ViewModelComponent::class)
class PokeDexUseCasesImpl @Inject constructor(private val remote: PokeDexUseCases): PokeDexUseCases{

    override suspend fun getPokemonNames(offset: Int, limit: Int): List<String> {
        return remote.getPokemonNames(offset, limit)
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return remote.getPokemon(name)
    }

    override suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon> {
        val names = remote.getPokemonNames(offset, limit)
        val pokemons = mutableListOf<Pokemon>()
        for (name in names) {
            pokemons.add(remote.getPokemon(name))
        }
        return pokemons
    }
}
