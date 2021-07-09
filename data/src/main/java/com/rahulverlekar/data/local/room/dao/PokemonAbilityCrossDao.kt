package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonAbilityCrossRefDB

@Dao
interface PokemonAbilityCrossDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonAbilityCrossRefDB)

    @Update
    suspend fun update(vararg obj: PokemonAbilityCrossRefDB)

    @Delete
    suspend fun delete(vararg obj: PokemonAbilityCrossRefDB)

    @Query("SELECT * from pokemonabilitycrossrefdb WHERE pokemonId = :pokemonId")
    suspend fun loadAll(pokemonId: String): List<PokemonAbilityCrossRefDB>
}