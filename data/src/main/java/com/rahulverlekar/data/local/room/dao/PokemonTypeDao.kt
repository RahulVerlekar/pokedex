package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.PokemonDB
import com.rahulverlekar.data.local.room.model.PokemonTypeDB

@Dao
interface PokemonTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: PokemonTypeDB)

    @Update
    suspend fun update(vararg obj: PokemonTypeDB)

    @Delete
    suspend fun delete(vararg obj: PokemonTypeDB)

    @Query("SELECT * FROM type WHERE id = :id")
    suspend fun load(id: Int): PokemonTypeDB

    @Query("SELECT * from type WHERE id IN (:ids)")
    suspend fun loadAll(ids: List<String>): List<PokemonTypeDB>
}