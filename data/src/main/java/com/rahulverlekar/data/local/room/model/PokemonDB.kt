package com.rahulverlekar.data.local.room.model

import androidx.room.*
import com.rahulverlekar.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonDB(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "image")
    var image: String,
)

data class PokemonAndData(
    @Embedded val pokemon: PokemonDB,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PokemonMoveCrossRefDB::class,
            parentColumn = "pokemonId",
            entityColumn = "moveId"
        )
    )
    val moves: List<MoveDB>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PokemonTypeCrossRefDB::class,
            parentColumn = "pokemonId",
            entityColumn = "typeId"
        )
    )
    val types: List<PokemonTypeDB>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            PokemonAbilityCrossRefDB::class,
            parentColumn = "pokemonId",
            entityColumn = "abilityId"
        )
    )
    val abilities: List<AbilityDB>
)

fun PokemonAndData.toDomain() = Pokemon(
    pokemon.id, pokemon.name, pokemon.image,
    abilities.map { it.toDomain() },
    moves.map { it.toDomain() },
    types.map { it.toDomain() }
)