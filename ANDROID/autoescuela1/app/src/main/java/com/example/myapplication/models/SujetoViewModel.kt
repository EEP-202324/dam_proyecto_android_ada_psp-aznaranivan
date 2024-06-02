package com.example.myapplication.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Sujeto
import com.example.myapplication.network.SujetoApi
import com.example.myapplication.repository.SujetoRepository
import kotlinx.coroutines.launch
import java.io.IOException


class SujetoViewModel : ViewModel() {
    sealed interface SujetoUiState {
        data class Success(val sujeto: Unit) : SujetoUiState
        object Error : SujetoUiState

        object Loading : SujetoUiState
    }
    var sujetoUiState: SujetoUiState by mutableStateOf(SujetoUiState.Loading)
        private set


    fun anadirSujetos(
        nombre : String, apellido : String, DNI : String
    ) {
        viewModelScope.launch {
            try {
                val nuevoSujeto = Sujeto(0, nombre, apellido, DNI)
                val exito = SujetoRepository(SujetoApi.retrofitService).addSujeto(nuevoSujeto)
                if (exito) {
                    sujetoUiState = SujetoUiState.Success(Unit)
                } else {
                    sujetoUiState = SujetoUiState.Error
                }
            } catch (e: IOException) {
                sujetoUiState = SujetoUiState.Error
                Log.e("SujetoViewModel", "Error al añadir un sujeto", e)
            }
        }
    }
}