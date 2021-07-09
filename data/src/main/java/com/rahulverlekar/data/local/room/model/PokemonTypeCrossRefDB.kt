package com.rahulverlekar.data.local.room.model

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "typeId"])
data class PokemonTypeCrossRefDB(
    var pokemonId: Int,
    var typeId: String
)