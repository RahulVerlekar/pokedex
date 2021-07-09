package com.rahulverlekar.data.network.repository

import com.rahulverlekar.data.network.common.RetrofitClient
import com.rahulverlekar.data.network.dto.PagedDTO
import com.rahulverlekar.data.network.dto.PokemonNamedResourceDTO
import com.rahulverlekar.data.temporary.KeyValueStorage
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokeDexNetworkRepo @Inject constructor(
    client: RetrofitClient,
    private val keyValueStorage: KeyValueStorage
) : PokeDexUseCases {

    private val api: PokeDexApi by lazy {
        client.build().create(PokeDexApi::class.java)
    }

    override suspend fun getPokemonNames(offset: Int, limit: Int): List<String> {
        return api.getAllPokemon(offset, limit).results?.map { it.name } ?: emptyList()
    }
}

interface PokeDexApi {

    @GET(value = "pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int = 20): PagedDTO<PokemonNamedResourceDTO>

}