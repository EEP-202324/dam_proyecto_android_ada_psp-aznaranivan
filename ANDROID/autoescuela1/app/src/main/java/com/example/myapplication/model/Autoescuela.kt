package com.example.myapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class Autoescuela(
    val id: Int,
    val nombre: String,
    val logotipo: String,
    val numeroCoches: Int
)
