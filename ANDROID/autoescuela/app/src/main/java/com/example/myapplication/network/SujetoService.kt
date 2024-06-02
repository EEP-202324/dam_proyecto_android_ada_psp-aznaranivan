package com.example.myapplication.network

import com.example.myapplication.model.Sujeto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
    "http://10.0.2.2:8080/"


interface SujetoApiService {
    @GET("sujetos")
    suspend fun getSujetos(): List<Sujeto>

    @PUT("sujetos/{id}")
    suspend fun updateSujeto(@Path("id") id: Long, @Body sujeto: Sujeto): Sujeto

    @DELETE("sujetos/{id}")
    suspend fun deleteSujeto(@Path("id") id: Int): Response<Void>

    @POST("sujetos")
    suspend fun addSujeto(@Body sujeto: Sujeto): Response<Void>
}

object SujetoApi {
    val retrofitService: SujetoApiService by lazy {
        retrofit.create(SujetoApiService::class.java)
    }
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
