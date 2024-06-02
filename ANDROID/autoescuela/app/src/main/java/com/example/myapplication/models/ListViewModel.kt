package com.example.myapplication.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Sujeto
import com.example.myapplication.network.SujetoApi
import kotlinx.coroutines.launch
import java.io.IOException

class ListViewModel : ViewModel() {
    sealed interface SujetoUiState {
        data class Success(val sujetos: List<Sujeto>) : SujetoUiState
        object Error : SujetoUiState
        object Loading : SujetoUiState
    }
    var sujetoUiState: SujetoUiState by mutableStateOf(SujetoUiState.Loading)
        private set

    init {
        recuperarSujetos()
    }

    fun recuperarSujetos() {
        viewModelScope.launch {
            try {
                Log.i("ViewModel","Antes de la lista");
                val listResult = SujetoApi.retrofitService.getSujetos()
                Log.i("ViewModel","Despues de la lista");
                sujetoUiState = SujetoUiState.Success(
                    listResult //"Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                sujetoUiState = SujetoUiState.Error
                Log.i("ViewModel", "Algo falló", e);
            }
        }
    }

    fun borrarSujeto(id: Int) {
        viewModelScope.launch {
            try {
                val respuesta = SujetoApi.retrofitService.deleteSujeto(id)
                if (respuesta.isSuccessful){
                    Log.i("borrarSujeto", "Sujeto borrado")
                    recuperarSujetos()
                }
            } catch (e: IOException) {
                sujetoUiState = SujetoUiState.Error
                Log.i("ViewModel", "Algo falló", e);
            }
        }
    }
}