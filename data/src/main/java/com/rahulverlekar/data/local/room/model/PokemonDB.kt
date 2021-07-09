package com.rahulverlekar.data.local.room.model

import androidx.room.*

@Entity(tableName = "pokemon")
data class PokemonDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var Id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image")
    var image: String,
)

data class PokemonAndData(
    @Embedded val pokemonDB: PokemonDB,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "moveId",
        associateBy = Junction(PokemonMoveCrossRefDB::class)
    )
    val moves: List<MoveDB>,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "typeId",
        associateBy = Junction(PokemonTypeCrossRefDB::class)
    )
    val types: List<PokemonTypeDB>,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "abilityId",
        associateBy = Junction(PokemonAbilityCrossRefDB::class)
    )
    val abilities: List<AbilityDB>
)

//fun PokemonDB.toDomain() = Pokemon(Id, firstName, lastName, phone, region, createdBy, createdOn)