package com.rahulverlekar.data.network.repository

import com.rahulverlekar.data.network.common.RetrofitClient
import com.rahulverlekar.data.network.dto.PagedDTO
import com.rahulverlekar.data.network.dto.PokemonDetailResponse
import com.rahulverlekar.data.network.dto.PokemonNamedResourceDTO
import com.rahulverlekar.data.network.mapper.toDomain
import com.rahulverlekar.domain.KeyValueStorage
import com.rahulverlekar.domain.model.Pokemon
import com.rahulverlekar.domain.usecase.PokeDexRemoteUseCases
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokeDexNetworkRepo @Inject constructor(
    client: RetrofitClient,
    private val keyValueStorage: KeyValueStorage
) : PokeDexRemoteUseCases {

    private val api: PokeDexApi by lazy {
        client.build().create(PokeDexApi::class.java)
    }

    override suspend fun getPokemonNames(offset: Int, limit: Int): List<String> {
        return api.getAllPokemon(offset, limit).results?.map { it.name } ?: emptyList()
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return api.getPokemon(name).toDomain()
    }

    override suspend fun getPokemons(offset: Int, limit: Int, order: String): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshList(offset: Int, limit: Int, order: String): List<Pokemon> {
        return getPokemons(offset, limit, order)
    }

    override suspend fun searchPokemon(search: String): List<Pokemon> {
        TODO("Not yet implemented")
    }
}

interface PokeDexApi {

    @GET(value = "pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int = 20): PagedDTO<PokemonNamedResourceDTO>

    @GET(value = "pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonDetailResponse

}