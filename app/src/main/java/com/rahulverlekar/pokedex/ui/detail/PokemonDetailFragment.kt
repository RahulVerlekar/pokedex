package com.rahulverlekar.pokedex.ui.detail

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.rahulverlekar.domain.model.Ability
import com.rahulverlekar.domain.model.Move
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.pokedex.R
import com.rahulverlekar.pokedex.base.BaseFragment
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.common.ListItem
import com.rahulverlekar.pokedex.common.RecyclerViewCallback
import com.rahulverlekar.pokedex.common.addDataSource
import com.rahulverlekar.pokedex.common.loadMore
import com.rahulverlekar.pokedex.databinding.FragmentPokemonDetailBinding
import com.rahulverlekar.pokedex.databinding.FragmentPokemonListBinding
import com.rahulverlekar.pokedex.ui.PokemonViewRes
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class PokemonDetailFragment :
    BaseFragment<FragmentPokemonDetailBinding>(R.layout.fragment_pokemon_detail), RecyclerViewCallback {

    private val viewModel: PokemonDetailViewModel by viewModels()
    private val args by navArgs<PokemonDetailFragmentArgs>()
    var activeTab: MutableLiveData<ActiveTab> = MutableLiveData(ActiveTab.Ability)

    override fun getVM(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElementTransitionOnEnter()
    }

    override fun attachBinding() {
        binding.item = PokemonViewRes(args.pokemon, requireContext())
        binding.handler = this
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onPokemonSelected(args.pokemon)
        viewModel.pokemon.observe(viewLifecycleOwner, { pokemon ->
            binding.rvAbility.addDataSource(pokemon.abilities.map { AbilityItem(it) }, R.layout.item_ability, this)
            binding.rvMove.addDataSource(pokemon.moves.map { MoveItem(it, requireContext()) }, R.layout.item_move, this)
        })
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }

    private fun startEnterTransitionAfterLoadingImage(
        imageAddress: String,
        imageView: ImageView
    ) {
        Glide.with(this)
            .load(imageAddress)
            .dontAnimate() // 1
            .listener(object : RequestListener<Drawable> { // 2
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }

    public enum class ActiveTab {Moves, Ability}

    fun onAbilitySelected() {
        activeTab.value = ActiveTab.Ability
        binding.rvAbility.visibility = View.VISIBLE
        binding.rvMove.visibility = View.GONE
    }

    fun onMoveSelected() {
        activeTab.value = ActiveTab.Moves
        binding.rvAbility.visibility = View.GONE
        binding.rvMove.visibility = View.VISIBLE
    }
}

data class AbilityItem(val ability: Ability): ListItem {
    val name
    get() = run { ability.name }

    val description
    get() = run { ability.name }
}
data class MoveItem(val move: Move, val ctx: Context): ListItem {
    val name
    get() = run { move.name }

    val typeIcon: Drawable?
    get() = when(Random(5445).nextInt(0,3)) {
        0->{
             ContextCompat.getDrawable(ctx, R.drawable.normal_icon)
        }
        1->{
            ContextCompat.getDrawable(ctx, R.drawable.water_icon)
        }
        2->{
            ContextCompat.getDrawable(ctx, R.drawable.fire_icon)
        }
        3->{
            ContextCompat.getDrawable(ctx, R.drawable.grass_icon)
        }
        else -> {
            ContextCompat.getDrawable(ctx, R.drawable.ghost_icon)
        }
    }
}
