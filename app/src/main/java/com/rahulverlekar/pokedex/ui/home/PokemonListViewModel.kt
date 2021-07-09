package com.rahulverlekar.pokedex.ui.home

import com.rahulverlekar.data.local.room.AppDatabase
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val useCases: PokeDexUseCases, private val appDatabase: AppDatabase): BaseViewModel() {
    init {
        appDatabase
        launch {
            val data = useCases.getPokemons(0, 5)
            println(data)
        }
    }
}