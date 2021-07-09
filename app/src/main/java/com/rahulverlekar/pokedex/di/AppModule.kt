package com.rahulverlekar.pokedex.di

import android.content.Context
import com.rahulverlekar.data.local.room.AppDatabase
import com.rahulverlekar.domain.KeyValueStorage
import com.rahulverlekar.data.temporary.KeyValueWithPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferences(obj: KeyValueWithPref): KeyValueStorage = obj

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDatabase = AppDatabase.getClient(context)

}