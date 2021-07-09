package com.rahulverlekar.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "move")
data class MoveDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var Id: Int,
    @ColumnInfo(name = "name")
    var name: String
)