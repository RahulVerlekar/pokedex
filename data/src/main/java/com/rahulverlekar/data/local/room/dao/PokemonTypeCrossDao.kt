package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonTypeCrossRefDB

@Dao
interface PokemonTypeCrossDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonTypeCrossRefDB)

    @Update
    suspend fun update(vararg obj: PokemonTypeCrossRefDB)

    @Delete
    suspend fun delete(vararg obj: PokemonTypeCrossRefDB)

    @Query("SELECT * from pokemontypecrossrefdb WHERE pokemonId = :pokemonId")
    suspend fun loadAll(pokemonId: String): List<PokemonTypeCrossRefDB>
}