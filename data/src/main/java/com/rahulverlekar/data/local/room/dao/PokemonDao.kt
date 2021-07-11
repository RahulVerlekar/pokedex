package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonAndData
import com.rahulverlekar.data.local.room.model.PokemonDB

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonDB)

    @Update
    suspend fun update(vararg obj: PokemonDB)

    @Delete
    suspend fun delete(vararg obj: PokemonDB)

    @Query("DELETE FROM pokemon")
    suspend fun delete()

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun loadPokemon(id: Int): PokemonDB

    @Query("SELECT * from pokemon WHERE id IN (:ids)")
    suspend fun loadAllPokemon(ids: List<String>): List<PokemonDB>

    @Query("SELECT * from pokemon")
    suspend fun loadAllPokemon(): List<PokemonDB>

    @Transaction
    @Query("SELECT * from pokemon LIMIT :limit OFFSET :offset")
    suspend fun loadAllPokemon(limit: Int, offset: Int): List<PokemonAndData>
}