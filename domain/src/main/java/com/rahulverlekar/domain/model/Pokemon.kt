package com.rahulverlekar.domain.model

import java.io.Serializable

interface NamedResource {
    val id: Int
    val name: String
}

data class Pokemon(
    override val id: Int,
    override val name: String,
    val description: String,
    val image: String,
    val abilities: List<Ability>,
    val moves: List<Move>,
    val types: List<Type>
) : NamedResource, Serializable

data class Ability(
    override val id: Int,
    override val name: String
) : NamedResource, Serializable

data class Move(
    override val id: Int,
    override val name: String
) : NamedResource, Serializable

data class Type(
    override val id: Int,
    override val name: String
) : NamedResource, Serializable