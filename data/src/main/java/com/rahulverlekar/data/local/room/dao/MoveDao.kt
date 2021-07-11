package com.rahulverlekar.data.local.room.dao

import androidx.room.*
import com.rahulverlekar.data.local.room.model.MoveDB

@Dao
interface MoveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: MoveDB)

    @Update
    suspend fun update(vararg obj: MoveDB)

    @Delete
    suspend fun delete(vararg obj: MoveDB)

    @Query("DELETE FROM move")
    suspend fun delete()

    @Query("SELECT * FROM move WHERE id = :id")
    suspend fun load(id: Int): MoveDB

    @Query("SELECT * from move WHERE id IN (:ids)")
    suspend fun loadAll(ids: List<String>): List<MoveDB>
}