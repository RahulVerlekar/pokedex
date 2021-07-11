package com.rahulverlekar.pokedex.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.pokedex.R
import com.rahulverlekar.pokedex.base.BaseFragment
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.common.*
import com.rahulverlekar.pokedex.databinding.FragmentPokemonListBinding
import com.rahulverlekar.pokedex.ui.PokemonViewRes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list), RecyclerViewCallback,
    SwipeRefreshLayout.OnRefreshListener {

    val viewModel: PokemonListViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel

    override fun attachBinding() {
        viewModel.pokemons.observe(viewLifecycleOwner, {list ->
            binding.rvPokemonList.addDataSource(list.withIndex().map { PokemonItem(it.value, requireContext(), it.index) }, R.layout.item_pokemon, this)
        })
        viewModel.isBusy.observe(viewLifecycleOwner, {
            binding.rlLoadMore.visibility = if(it == true) View.VISIBLE else View.GONE
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivLoading.loadGif(R.raw.pika_loading)
        binding.rvPokemonList.loadMore { viewModel.loadMore() }
        binding.slPokemonList.setOnRefreshListener(this)
    }

    override fun onClick(item: ListItem) {
        if (item is PokemonItem) {
            val view = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<ImageView>(R.id.iv_pokemon)
            val tv = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<TextView>(R.id.txt_name)
            val extra = FragmentNavigatorExtras(
            )
            // TODO: 11-07-2021 Work on animations
            findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(item.pokemon))
        }
    }

    override fun onRefresh() {
        viewModel.onRefresh()
    }
}

data class PokemonItem(override val pokemon: Pokemon, override val ctx: Context, val pos: Int) : PokemonViewRes(pokemon, ctx), ListItem {
    val name: String
        get() = pokemon.name

    val image: String
        get() = pokemon.image

    val typeOne: String
        get() = pokemon.types[0].name

    val typeTwo: String
        get() = pokemon.types[1].name
}