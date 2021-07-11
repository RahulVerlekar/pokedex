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
import com.rahulverlekar.pokedex.utils.BaseEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list), RecyclerViewCallback,
    SwipeRefreshLayout.OnRefreshListener {

    val viewModel: PokemonListViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel

    override fun attachBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivLoading.loadGif(R.raw.pika_loading)
        binding.ivNoData.loadGifStretch(R.raw.ash_run)
        binding.rvPokemonList.loadMore { viewModel.loadMore() }
        binding.slPokemonList.setOnRefreshListener(this)
        viewModel.pokemons.observe(viewLifecycleOwner, {list ->
            if(list.isEmpty()) {
                binding.llNoData.visibility = View.VISIBLE
            }
            else {
                binding.llNoData.visibility = View.GONE
            }
            binding.rvPokemonList.addDataSource(list.withIndex().map { PokemonItem(it.value, requireContext(), it.index) }, R.layout.item_pokemon_card, this)
        })
        viewModel.isBusy.observe(viewLifecycleOwner, {
            binding.rlLoadMore.visibility = if(it == true) View.VISIBLE else View.GONE
        })
    }

    override fun onClick(item: ListItem) {
        if (item is PokemonItem) {
            val view = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<ImageView>(R.id.iv_pokemon)
            val tv = (binding.rvPokemonList.findViewHolderForLayoutPosition(item.pos) as DataBindingVH).binding.root.findViewById<TextView>(R.id.txt_name)
            val extra = FragmentNavigatorExtras(
                view to item.image,
                tv to item.name
            )
            // TODO: 11-07-2021 Work on animations
            findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(item.pokemon), extra)
        }
    }

    override fun handleEvent(event: BaseEvent) {
        super.handleEvent(event)
        if (event is PokemonListViewModel.RefreshCompleteEvent) {
            binding.slPokemonList.isRefreshing = false
        }
        if (event is PokemonListViewModel.OpenSearchPageEvent) {
            findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonSearchFragment())
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