package com.rahulverlekar.data.network.repository

import com.rahulverlekar.data.network.common.RetrofitClient
import com.rahulverlekar.data.temporary.KeyValueStorage
import com.rahulverlekar.domain.usecase.PokeDexUseCases
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
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

    override suspend fun getAllPokemon(): String {
        return api.getAllPokemon() ?: ""
    }
}

interface PokeDexApi {

    @GET(value = "pokemon")
    suspend fun getAllPokemon(): String

}