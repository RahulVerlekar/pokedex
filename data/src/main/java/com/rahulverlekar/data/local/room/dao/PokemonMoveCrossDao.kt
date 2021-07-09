package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonMoveCrossRefDB

@Dao
interface PokemonMoveCrossDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonMoveCrossRefDB)

    @Update
    suspend fun update(vararg obj: PokemonMoveCrossRefDB)

    @Delete
    suspend fun delete(vararg obj: PokemonMoveCrossRefDB)

    @Query("SELECT * from pokemonmovecrossrefdb WHERE pokemonId = :pokemonId")
    suspend fun loadAll(pokemonId: String): List<PokemonMoveCrossRefDB>
}