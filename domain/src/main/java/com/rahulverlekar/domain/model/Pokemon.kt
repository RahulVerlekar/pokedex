package com.rahulverlekar.domain.model

interface NamedResource {
    val id: Int
    val name: String
}

data class Pokemon(
    override val id: Int,
    override val name: String,
    val image: String,
    val abilities: List<Ability>,
    val moves: List<Move>,
    val types: List<Type>
    ): NamedResource

data class Ability(
    override val id: Int,
    override val name: String
): NamedResource

data class Move(
    override val id: Int,
    override val name: String
): NamedResource

data class Type(
    override val id: Int,
    override val name: String
): NamedResource