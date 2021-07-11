package com.rahulverlekar.pokedex.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val useCases: PokeDexUseCases) :
    BaseViewModel() {

    val pokemon = MutableLiveData<Pokemon>()

    fun onPokemonSelected(pokemon: Pokemon) {
        this.pokemon.value = pokemon
    }
}