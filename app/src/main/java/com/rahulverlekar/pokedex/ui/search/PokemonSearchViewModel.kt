package com.rahulverlekar.pokedex.ui.search

import androidx.lifecycle.MutableLiveData
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.utils.BaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.properties.ObservableProperty

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(private val useCases: PokeDexUseCases) :
    BaseViewModel() {

    val pokemons = MutableLiveData<List<Pokemon>>()
    val search = MutableLiveData<String>("")

    init {
        search.observeForever {
            search()
        }
    }

    fun search() {
        launch {
            search.value?.let {
                val data = useCases.searchPokemon("%$it%")
                pokemons.value = data
            }
        }
    }
}
