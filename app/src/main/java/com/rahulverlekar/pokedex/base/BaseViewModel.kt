package com.rahulverlekar.pokedex.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulverlekar.pokedex.utils.BaseEvent
import com.rahulverlekar.pokedex.utils.ErrorEvent
import com.solarsack.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val bus: SingleLiveEvent<BaseEvent> = SingleLiveEvent()
    val isBusy = MutableLiveData<Boolean>()

    fun sendEvent(event: BaseEvent) {
        bus.postValue(event)
    }

    //Make async network call
    fun launch(
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                isBusy.postValue(true)
                block()
                isBusy.postValue(false)
            } catch (e: Exception) {
                isBusy.postValue(false)
                sendEvent(ErrorEvent(e.message ?: ""))
            }
        }
    }
}