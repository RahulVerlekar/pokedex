package com.rahulverlekar.pokedex.ui.home

import androidx.fragment.app.viewModels
import com.rahulverlekar.pokedex.R
import com.rahulverlekar.pokedex.base.BaseFragment
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list) {

    val viewModel: PokemonListViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel


}