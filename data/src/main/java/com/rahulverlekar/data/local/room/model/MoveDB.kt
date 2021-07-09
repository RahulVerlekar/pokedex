package com.rahulverlekar.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahulverlekar.domain.model.Move

@Entity(tableName = "move")
data class MoveDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String
)

fun MoveDB.toDomain(): Move {
    return Move(id, name)
}
fun Move.toData(): MoveDB {
    return MoveDB(id, name)
}