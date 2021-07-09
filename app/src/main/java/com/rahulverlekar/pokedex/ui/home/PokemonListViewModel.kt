package com.rahulverlekar.pokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rahulverlekar.data.local.room.AppDatabase
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val useCases: PokeDexUseCases) :
    BaseViewModel() {

    val pokemons = MutableLiveData<List<Pokemon>>()

    init {
        launch {
            val data = useCases.getPokemons(0, 10)
            pokemons.value = data
            println(data)
        }
    }

    fun loadMore() {
        launch {
            val data = useCases.getPokemons(pokemons.value?.size ?: 0, 10)
            val newData = pokemons.value?.plus(data)
            newData?.let {
                pokemons.value = it
            }
        }
    }

}