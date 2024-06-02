package com.example.myapplication.network

import com.example.myapplication.model.Autoescuela
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


interface AutoescuelaApiService {
    @GET("autoescuelas")
    suspend fun getAutoescuelas(): List<Autoescuela>

    @PUT("autoescuelas/{id}")
    suspend fun updateAutoescuela(@Path("id") id: Long, @Body autoescuela: Autoescuela): Autoescuela

    @DELETE("autoescuelas/{id}")
    suspend fun deleteAutoescuela(@Path("id") id: Long): Unit

    @POST("autoescuelas")
    suspend fun addAutoescuela(@Body autoescuela: Autoescuela): Response<Void>
}

object AutoescuelaApi {
    val retrofitService: AutoescuelaApiService by lazy {
        retrofit.create(AutoescuelaApiService::class.java)
    }
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
