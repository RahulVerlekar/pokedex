package com.rahulverlekar.pokedex.ui.home

import androidx.lifecycle.MutableLiveData
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.utils.BaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val useCases: PokeDexUseCases) :
    BaseViewModel() {

    private val limit = 10
    val pokemons = MutableLiveData<List<Pokemon>>()
    var order: OrderBy = OrderBy.Number

    init {
        launch {
            val data = useCases.getPokemons(0, limit, order.column)
            pokemons.value = data
        }
    }

    fun loadMore() {
        launch {
            val data = useCases.getPokemons(pokemons.value?.size ?: 0, limit, order.column)
            val newData = pokemons.value?.plus(data)
            newData?.let {
                pokemons.value = it
            }
        }
    }

    fun onRefresh() {
        launch {
            pokemons.value = emptyList()
            val data = useCases.refreshList(0, limit, order.column)
            pokemons.value = data
            sendEvent(RefreshCompleteEvent())
        }
    }

    fun orderByName() {
        order = OrderBy.Name
    }

    fun orderByNumber() {
        order = OrderBy.Number
    }

    fun typeClicked() {
        order = if (order == OrderBy.Name) OrderBy.Number else OrderBy.Name
        pokemons.value = emptyList()
        loadMore()
    }

    fun searchClicked() {
        sendEvent(OpenSearchPageEvent())
    }

    class RefreshCompleteEvent() : BaseEvent()
    class OpenSearchPageEvent() : BaseEvent()
    enum class OrderBy(val column: String) { Name("name"), Number("id") }
}
