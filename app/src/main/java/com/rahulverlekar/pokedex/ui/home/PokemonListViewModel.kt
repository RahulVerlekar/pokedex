package com.rahulverlekar.pokedex.ui.home

import com.rahulverlekar.data.temporary.KeyValueStorage
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val keyValueStorage: KeyValueStorage): BaseViewModel() {
}