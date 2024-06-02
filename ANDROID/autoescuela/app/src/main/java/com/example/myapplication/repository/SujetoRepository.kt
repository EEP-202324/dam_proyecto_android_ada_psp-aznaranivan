package com.example.myapplication.repository

import com.example.myapplication.model.Sujeto
import com.example.myapplication.network.SujetoApiService

class SujetoRepository(private val sujetoService: SujetoApiService) {
    suspend fun addSujeto(sujeto: Sujeto): Boolean {
        val response = sujetoService.addSujeto(sujeto)
        return response.isSuccessful
    }
    suspend fun borrarSujeto(id: Int): Boolean {
        val response = sujetoService.deleteSujeto(id)
        return response.isSuccessful
    }
}