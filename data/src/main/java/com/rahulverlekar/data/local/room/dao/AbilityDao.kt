package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.AbilityDB

@Dao
interface AbilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: AbilityDB)

    @Update
    suspend fun update(vararg obj: AbilityDB)

    @Delete
    suspend fun delete(vararg obj: AbilityDB)

    @Query("DELETE FROM ability")
    suspend fun delete()

    @Query("SELECT * FROM ability WHERE id = :id")
    suspend fun load(id: Int): AbilityDB

    @Query("SELECT * from ability WHERE id IN (:ids)")
    suspend fun loadAll(ids: List<String>): List<AbilityDB>
}