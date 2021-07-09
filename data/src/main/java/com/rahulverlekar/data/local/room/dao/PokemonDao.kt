package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonDB

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonDB)

    @Update
    suspend fun update(vararg obj: PokemonDB)

    @Delete
    suspend fun delete(vararg obj: PokemonDB)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun loadPokemon(id: Int): PokemonDB

    @Query("SELECT * from pokemon WHERE id IN (:ids)")
    suspend fun loadAllPokemon(ids: List<String>): List<PokemonDB>
}