package com.rahulverlekar.data.local.room.repository

import com.rahulverlekar.data.local.room.AppDatabase
import com.rahulverlekar.data.local.room.dao.*
import com.rahulverlekar.data.local.room.model.*
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexLocalUseCases
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokeDexDB @Inject constructor(
    private val database: AppDatabase
) : PokeDexLocalUseCases {

    override suspend fun addPokemon(vararg objects: Pokemon) {
        val pokemons = objects.map { PokemonDB(it.id, it.name, it.image) }
        database.pokemon().insert(*pokemons.toTypedArray())

        val moves = objects.flatMap { it.moves }.distinctBy { it.id }.map { MoveDB(it.id, it.name) }
        database.move().insert(*moves.toTypedArray())

        val abilities = objects.flatMap { it.abilities }.distinctBy { it.id }.map { AbilityDB(it.id, it.name) }
        database.ability().insert(*abilities.toTypedArray())

        val types = objects.flatMap { it.types }.distinctBy { it.id }.map { PokemonTypeDB(it.id, it.name) }
        database.pokemonType().insert(*types.toTypedArray())

        val typesCross = objects.flatMap { pokemon ->
            pokemon.types.map { type ->
                PokemonTypeCrossRefDB(pokemon.id, type.id)
            }
        }
        database.typeCross().insert(*typesCross.toTypedArray())

        val abilitiesCross = objects.flatMap { pokemon ->
            pokemon.abilities.map { ability ->
                PokemonAbilityCrossRefDB(pokemon.id, ability.id)
            }
        }
        database.abilityCross().insert(*abilitiesCross.toTypedArray())

        val movesCross = objects.flatMap { pokemon ->
            pokemon.moves.map { move ->
                PokemonMoveCrossRefDB(pokemon.id, move.id)
            }
        }
        database.moveCross().insert(*movesCross.toTypedArray())
    }

    override suspend fun getPokemon(name: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon> {
        return database.pokemon().loadAllPokemon(limit, offset).map { it.toDomain() }
    }

    override suspend fun refreshList(offset: Int, limit: Int): List<Pokemon> {
        database.pokemon().delete()
        database.pokemonType().delete()
        database.move().delete()
        database.ability().delete()
        database.typeCross().delete()
        database.moveCross().delete()
        database.moveCross().delete()
        database.abilityCross().delete()
        return emptyList()
    }
}