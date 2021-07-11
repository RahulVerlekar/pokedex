package com.rahulverlekar.pokedex.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.model.Type
import com.rahulverlekar.pokedex.R

class Extentions {
}

open class PokemonViewRes(open val pokemon: Pokemon, open val ctx: Context) {

    fun getTypeTwo(): Type? {
        return pokemon.types.getOrNull(1)
    }

    val typeOneColor: Int
        get() = pokemon.types[0].toColor(ctx)

    val typeOneIcon: Drawable?
        get() = pokemon.types[0].toIcon(ctx)

    val typeOneTag: Drawable?
        get() = pokemon.types[0].toTag(ctx)

    val typeTwoColor: Int?
        get() = getTypeTwo()?.toColor(ctx)

    val typeTwoIcon: Drawable?
        get() = getTypeTwo()?.toIcon(ctx)

    val typeTwoTag: Drawable?
        get() = getTypeTwo()?.toTag(ctx)

}

fun Type.toTag(ctx: Context): Drawable? {
    when(name) {
        "normal"->{
            return ContextCompat.getDrawable(ctx, R.drawable.normal_tag)
        }
        "fighting"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fight_tag)
        }
        "flying"->{
            return ContextCompat.getDrawable(ctx, R.drawable.flying_tag)
        }
        "poison"->{
            return ContextCompat.getDrawable(ctx, R.drawable.poison_tag)
        }
        "ground"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ground_tag)
        }
        "rock"->{
            return ContextCompat.getDrawable(ctx, R.drawable.rock_tag)
        }
        "bug"->{
            return ContextCompat.getDrawable(ctx, R.drawable.bug_tag)
        }
        "ghost"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ghost_tag)
        }
        "steel"->{
            return ContextCompat.getDrawable(ctx, R.drawable.steel_tag)
        }
        "fire"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fire_tag)
        }
        "water"->{
            return ContextCompat.getDrawable(ctx, R.drawable.water_tag)
        }
        "grass"->{
            return ContextCompat.getDrawable(ctx, R.drawable.grass_tag)
        }
        "electric"->{
            return ContextCompat.getDrawable(ctx, R.drawable.electric_tag)
        }
        "psychic"->{
            return ContextCompat.getDrawable(ctx, R.drawable.psychic_tag)
        }
        "ice"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ice_tag)
        }
        "dragon"->{
            return ContextCompat.getDrawable(ctx, R.drawable.dragon_tag)
        }
        "dark"->{
            return ContextCompat.getDrawable(ctx, R.drawable.dark_tag)
        }
        "fairy"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fairy_tag)
        }
        else -> {
            return ContextCompat.getDrawable(ctx, R.drawable.water_tag)
        }
    }
}

fun Type.toIcon(ctx: Context): Drawable? {
    when(name) {
        "normal"->{
            return ContextCompat.getDrawable(ctx, R.drawable.normal_icon)
        }
        "fighting"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fight_icon)
        }
        "flying"->{
            return ContextCompat.getDrawable(ctx, R.drawable.flying_icon)
        }
        "poison"->{
            return ContextCompat.getDrawable(ctx, R.drawable.poison_icon)
        }
        "ground"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ground_icon)
        }
        "rock"->{
            return ContextCompat.getDrawable(ctx, R.drawable.rock_icon)
        }
        "bug"->{
            return ContextCompat.getDrawable(ctx, R.drawable.bug_icon)
        }
        "ghost"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ghost_icon)
        }
        "steel"->{
            return ContextCompat.getDrawable(ctx, R.drawable.steel_icon)
        }
        "fire"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fire_icon)
        }
        "water"->{
            return ContextCompat.getDrawable(ctx, R.drawable.water_icon)
        }
        "grass"->{
            return ContextCompat.getDrawable(ctx, R.drawable.grass_icon)
        }
        "electric"->{
            return ContextCompat.getDrawable(ctx, R.drawable.electric_icon)
        }
        "psychic"->{
            return ContextCompat.getDrawable(ctx, R.drawable.psychic_icon)
        }
        "ice"->{
            return ContextCompat.getDrawable(ctx, R.drawable.ice_icon)
        }
        "dragon"->{
            return ContextCompat.getDrawable(ctx, R.drawable.dragon_icon)
        }
        "dark"->{
            return ContextCompat.getDrawable(ctx, R.drawable.dark_icon)
        }
        "fairy"->{
            return ContextCompat.getDrawable(ctx, R.drawable.fairy_icon)
        }
        else -> {
            return ContextCompat.getDrawable(ctx, R.drawable.water_icon)
        }
    }
}

fun Type.toColor(ctx: Context): Int {
    when(name) {
        "normal"->{
            return ContextCompat.getColor(ctx, R.color.normalColor)
        }
        "fighting"->{
            return ContextCompat.getColor(ctx, R.color.fightingColor)
        }
        "flying"->{
            return ContextCompat.getColor(ctx, R.color.flyingColor)
        }
        "poison"->{
            return ContextCompat.getColor(ctx, R.color.poisonColor)
        }
        "ground"->{
            return ContextCompat.getColor(ctx, R.color.groundColor)
        }
        "rock"->{
            return ContextCompat.getColor(ctx, R.color.rockColor)
        }
        "bug"->{
            return ContextCompat.getColor(ctx, R.color.bugColor)
        }
        "ghost"->{
            return ContextCompat.getColor(ctx, R.color.ghostColor)
        }
        "steel"->{
            return ContextCompat.getColor(ctx, R.color.steelColor)
        }
        "fire"->{
            return ContextCompat.getColor(ctx, R.color.fireColor)
        }
        "water"->{
            return ContextCompat.getColor(ctx, R.color.waterColor)
        }
        "grass"->{
            return ContextCompat.getColor(ctx, R.color.grassColor)
        }
        "electric"->{
            return ContextCompat.getColor(ctx, R.color.electricColor)
        }
        "psychic"->{
            return ContextCompat.getColor(ctx, R.color.psychicColor)
        }
        "ice"->{
            return ContextCompat.getColor(ctx, R.color.iceColor)
        }
        "dragon"->{
            return ContextCompat.getColor(ctx, R.color.dragonColor)
        }
        "dark"->{
            return ContextCompat.getColor(ctx, R.color.darkColor)
        }
        "fairy"->{
            return ContextCompat.getColor(ctx, R.color.fairyColor)
        }
        else -> {
            return ContextCompat.getColor(ctx, R.color.waterColor)
        }
    }
}

