package com.rahulverlekar.data.temporary

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class KeyValueWithPref @Inject constructor(@ApplicationContext val applicationContext: Context) :
    KeyValueStorage {

    private val fileName = "prefs"

    private val localStore: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    private val TOKEN_KEY = "token"
    override var token: String?
        get() = localStore.getString(TOKEN_KEY, null)
        set(value) = localStore.edit().putString(TOKEN_KEY, value).apply()

    private val OFFSET = "offset"
    override var offset: Int?
        get() = localStore.getInt(OFFSET, 0)
        set(value) = localStore.edit().putInt(OFFSET, value?:0).apply()

    private val COUNT = "count"
    override var count: Int?
        get() = localStore.getInt(COUNT, 0)
        set(value) = localStore.edit().putInt(COUNT, value?:0).apply()

}