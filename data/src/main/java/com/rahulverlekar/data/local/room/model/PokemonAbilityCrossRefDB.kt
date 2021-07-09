package com.rahulverlekar.data.local.room.model

import androidx.room.Entity

@Entity(primaryKeys = ["pokemonId", "abilityId"])
data class PokemonAbilityCrossRefDB(
    var pokemonId: Int,
    var abilityId: String
)