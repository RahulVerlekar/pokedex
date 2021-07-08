package com.rahulverlekar.pokedex.di

import com.rahulverlekar.data.temporary.KeyValueStorage
import com.rahulverlekar.data.temporary.KeyValueWithPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferences(obj: KeyValueWithPref): KeyValueStorage = obj

}