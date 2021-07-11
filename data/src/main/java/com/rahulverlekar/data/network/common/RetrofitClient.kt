package com.rahulverlekar.data.network.common

import com.google.gson.GsonBuilder
import com.rahulverlekar.domain.KeyValueStorage
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class RetrofitClient @Inject constructor(private val keyValueStorage: KeyValueStorage) {
    private val builder : OkHttpClient.Builder by lazy {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val builder = it.request().newBuilder()

                this.keyValueStorage.token?.let { token ->
                    builder.addHeader("Authorization","Bearer $token")
                }
                it.proceed(builder.build())
            }

        builder
    }

    public fun build(baseUrl: String): Retrofit {
        val gsonBuilder = GsonBuilder()
        val converter = GsonConverterFactory.create(gsonBuilder.create())
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(ErrorAdapterFactory())
            .client(builder.build())
            .build()
    }

    public fun build(): Retrofit {
        return build("https://pokeapi.co/api/v2/")
    }
}
