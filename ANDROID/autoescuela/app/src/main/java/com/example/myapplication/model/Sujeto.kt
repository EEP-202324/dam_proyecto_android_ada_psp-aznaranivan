package com.example.myapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class Sujeto(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val DNI: String
)
