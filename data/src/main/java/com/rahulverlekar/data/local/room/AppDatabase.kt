package com.rahulverlekar.data.local.room

import android.content.Context
import androidx.room.*
import com.rahulverlekar.data.local.room.dao.*
import com.rahulverlekar.data.local.room.model.*

@Database(entities = arrayOf(
    PokemonDB::class,
    MoveDB::class,
    PokemonMoveCrossRefDB::class,
    AbilityDB::class,
    PokemonAbilityCrossRefDB::class,
    PokemonTypeDB::class,
    PokemonTypeCrossRefDB::class
), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemon() : PokemonDao

    abstract fun pokemonType() : PokemonTypeDao

    abstract fun move() : MoveDao

    abstract fun ability() : AbilityDao

    abstract fun typeCross() : PokemonTypeCrossDao

    abstract fun moveCross() : PokemonMoveCrossDao

    abstract fun abilityCross() : PokemonAbilityCrossDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getClient(context: Context) : AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "Database")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}