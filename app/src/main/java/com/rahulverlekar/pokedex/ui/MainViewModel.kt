package com.rahulverlekar.pokedex.ui

import com.rahulverlekar.domain.KeyValueStorage
import com.rahulverlekar.pokedex.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val keyValueStorage: KeyValueStorage): BaseViewModel() {

}