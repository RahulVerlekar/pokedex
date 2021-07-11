package com.rahulverlekar.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahulverlekar.domain.model.Ability

@Entity(tableName = "ability")
data class AbilityDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String
)

fun AbilityDB.toDomain(): Ability {
    return Ability(id, name)
}
fun Ability.toData(): AbilityDB {
    return AbilityDB(id, name)
}