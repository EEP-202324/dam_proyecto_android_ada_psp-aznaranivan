package com.example.myapplication.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Autoescuela
import com.example.myapplication.network.AutoescuelaApi
import kotlinx.coroutines.launch
import java.io.IOException

class AutoescuelasViewModel : ViewModel() {
    sealed interface AutoescuelaUiState {
        data class Success(val autoescuelas: List<Autoescuela>) : AutoescuelaUiState
        object Error : AutoescuelaUiState
        object Loading : AutoescuelaUiState
    }
    var sujetoUiState: AutoescuelaUiState by mutableStateOf(AutoescuelaUiState.Loading)
        private set

    init {
        recuperarAutoescuelas()
    }

    fun recuperarAutoescuelas() {
        viewModelScope.launch {
            try {
                Log.i("ViewModel","Antes de la lista");
                val listResult = AutoescuelaApi.retrofitService.getAutoescuelas()
                Log.i("ViewModel","Despues de la lista");
                sujetoUiState = AutoescuelaUiState.Success(
                    listResult //"Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                sujetoUiState = AutoescuelaUiState.Error
                Log.i("ViewModel", "Algo falló", e);
            }
        }
    }
}