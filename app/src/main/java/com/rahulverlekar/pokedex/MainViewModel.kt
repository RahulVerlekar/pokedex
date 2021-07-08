package com.rahulverlekar.pokedex

import com.rahulverlekar.data.temporary.KeyValueStorage
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val keyValueStorage: KeyValueStorage): BaseViewModel() {

}