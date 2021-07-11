package com.rahulverlekar.pokedex.ui.search

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rahulverlekar.pokedex.R
import com.rahulverlekar.pokedex.base.BaseFragment
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.common.*
import com.rahulverlekar.pokedex.databinding.FragmentPokemonSearchBinding
import com.rahulverlekar.pokedex.ui.home.PokemonItem
import com.rahulverlekar.pokedex.utils.BaseEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonSearchFragment :
    BaseFragment<FragmentPokemonSearchBinding>(R.layout.fragment_pokemon_search), RecyclerViewCallback {

    val viewModel: PokemonSearchViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel

    override fun attachBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pokemons.observe(viewLifecycleOwner, {list ->
            binding.rvPokemonList.addDataSource(list.withIndex().map { PokemonItem(it.value, requireContext(), it.index) }, R.layout.item_pokemon, this)
        })
    }

    override fun onClick(item: ListItem) {
        if (item is PokemonItem) {
            val view = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<ImageView>(R.id.iv_pokemon)
            val tv = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<TextView>(R.id.txt_name)
            val extra = FragmentNavigatorExtras(
            )
            // TODO: 11-07-2021 Work on animations
            findNavController().navigate(PokemonSearchFragmentDirections.actionPokemonSearchFragmentToPokemonDetailFragment(item.pokemon))
        }
    }

    override fun handleEvent(event: BaseEvent) {
        super.handleEvent(event)
    }
}