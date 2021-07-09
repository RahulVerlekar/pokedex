package com.rahulverlekar.data.network.mapper

import com.rahulverlekar.data.network.dto.AbilityDTO
import com.rahulverlekar.data.network.dto.MoveDTO
import com.rahulverlekar.data.network.dto.PokemonDetailResponse
import com.rahulverlekar.data.network.dto.TypeDTO
import com.rahulverlekar.domain.model.Ability
import com.rahulverlekar.domain.model.Move
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.model.Type

private fun String.urlToId(): Int {
    return "/-?[0-9]+/$".toRegex().find(this)!!.value.filter { it.isDigit() || it == '-' }.toInt()
}

fun PokemonDetailResponse.toDomain(): Pokemon {
    return Pokemon(
        id,
        name,
        sprites?.frontDefault ?: "",
        abilities.map { it.abilityDTO.toDomain() },
        moves.map { it.moveDTO.toDomain() },
        types.map { it.type.toDomain() }
    )
}

fun AbilityDTO.toDomain(): Ability {
    return Ability(url.urlToId(), name)
}

fun MoveDTO.toDomain(): Move {
    return Move(url.urlToId(), name)
}

fun TypeDTO.toDomain(): Type {
    return Type(url.urlToId(), name)
}