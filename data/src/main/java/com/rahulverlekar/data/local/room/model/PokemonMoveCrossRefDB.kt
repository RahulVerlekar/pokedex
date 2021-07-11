package com.rahulverlekar.data.local.room.model

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "moveId"])
data class PokemonMoveCrossRefDB(
    var pokemonId: Int,
    var moveId: Int
)