package com.rahulverlekar.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahulverlekar.domain.model.Type

@Entity(tableName = "type")
data class PokemonTypeDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String
)

fun PokemonTypeDB.toDomain(): Type {
    return Type(id, name)
}
fun Type.toData(): PokemonTypeDB {
    return PokemonTypeDB(id, name)
}