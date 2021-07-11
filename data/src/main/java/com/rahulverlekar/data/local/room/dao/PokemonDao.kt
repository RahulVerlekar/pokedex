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
    @Query("SELECT * from pokemon ORDER BY name LIMIT :limit OFFSET :offset")
    suspend fun loadAllPokemonByName(limit: Int, offset: Int): List<PokemonAndData>

    @Transaction
    @Query("SELECT * from pokemon ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun loadAllPokemonByNumber(limit: Int, offset: Int): List<PokemonAndData>

    @Transaction
    @Query("Select p.*" +
            "  from pokemon p" +
            " LEFT OUTER JOIN pokemonabilitycrossrefdb pa" +
            "  on p.id = pa.pokemonId" +
            " LEFT OUTER JOIN ability a" +
            " on pa.abilityId = a.id" +
            " Where p.name LIKE :search  or a.name LIKE :search GROUP BY p.id")
    suspend fun searchPokemon(search: String): List<PokemonAndData>
}