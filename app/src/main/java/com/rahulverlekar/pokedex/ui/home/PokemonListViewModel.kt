package com.rahulverlekar.pokedex.ui.home

import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val useCases: PokeDexUseCases): BaseViewModel() {
    init {
        launch {
            val data = useCases.getPokemonNames()
            println(data)
        }
    }
}