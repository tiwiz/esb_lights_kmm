package com.rob.lights.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rob.lights.shared.LightRepository
import com.rob.lights.shared.Lights
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class LightsViewModel : ViewModel() {

    private val repository by lazy {
        LightRepository()
    }

    private val _state : MutableLiveData<Lce<Lights>> = MutableLiveData()

    val state : LiveData<Lce<Lights>>
        get() = _state

    fun loadLights() {
        _state.postValue(Lce.Loading)

        viewModelScope.launch {
            try {
                val lights = repository.getLights()
                _state.postValue(Lce.Success(lights))
                println("SUCCESS")
            } catch(e: Exception) {
                _state.postValue(Lce.Error(e))
                println("Error: ${e.message}")
            }
        }
    }
}
