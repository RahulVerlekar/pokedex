package com.rahulverlekar.pokedex.ui.home

import androidx.fragment.app.viewModels
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.pokedex.R
import com.rahulverlekar.pokedex.base.BaseFragment
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.common.ListItem
import com.rahulverlekar.pokedex.common.RecyclerViewCallback
import com.rahulverlekar.pokedex.common.addDataSource
import com.rahulverlekar.pokedex.common.loadMore
import com.rahulverlekar.pokedex.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list), RecyclerViewCallback {

    val viewModel: PokemonListViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel

    override fun attachBinding() {
        viewModel.pokemons.observe(viewLifecycleOwner, {
            binding.rvPokemonList.addDataSource(it.map { PokemonItem(it) }, R.layout.item_pokemon, this)
        })
        binding.rvPokemonList.loadMore { viewModel.loadMore() }
    }
}

data class PokemonItem(val pokemon: Pokemon) : ListItem {
    val name: String
        get() = pokemon.name

    val image: String
        get() = pokemon.image

    val typeOne: String
        get() = pokemon.types[0].name

    val typeTwo: String
        get() = pokemon.types[1].name
}