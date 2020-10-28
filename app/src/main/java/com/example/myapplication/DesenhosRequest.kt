package com.example.myapplication

import retrofit2.Call
import retrofit2.http.*

interface DesenhosRequest {

    @GET("Galeria/{id}")
    fun getDesenhos(@Path("id")id:Integer): Call<Desenhos>
}