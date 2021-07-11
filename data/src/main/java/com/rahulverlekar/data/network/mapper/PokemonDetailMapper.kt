package com.rahulverlekar.data.network.mapper

import com.rahulverlekar.data.network.dto.AbilityDTO
import com.rahulverlekar.data.network.dto.MoveDTO
import com.rahulverlekar.data.network.dto.PokemonDetailResponse
import com.rahulverlekar.data.network.dto.TypeDTO
import com.rahulverlekar.domain.model.Ability
import com.rahulverlekar.domain.model.Move
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.model.Type
import java.util.*

private fun String.urlToId(): Int {
    return "/-?[0-9]+/$".toRegex().find(this)!!.value.filter { it.isDigit() || it == '-' }.toInt()
}

fun PokemonDetailResponse.toDomain(): Pokemon {
    return Pokemon(
        id,
        name.capitalizeWithLocale(),
        "Intro for the pokemon $name will come over here.",
        sprites?.other?.officialArtwork?.frontDefault ?: "",
        abilities.map { it.abilityDTO.toDomain() },
        moves.map { it.moveDTO.toDomain() },
        types.map { it.type.toDomain() }
    )
}

fun AbilityDTO.toDomain(): Ability {
    return Ability(url.urlToId(), name.capitalizeWithLocale())
}

fun MoveDTO.toDomain(): Move {
    return Move(url.urlToId(), name.capitalizeWithLocale())
}

fun TypeDTO.toDomain(): Type {
    return Type(url.urlToId(), name)
}

fun String.capitalizeWithLocale() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}