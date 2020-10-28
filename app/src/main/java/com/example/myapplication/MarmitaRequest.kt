package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarmitaRequest {

    @POST("/Galeria")
    fun postMarmita(@Body novaMarmita:Marmita): Call<Void>

    @GET("/Galeria")
    fun getMarmitas(): Call<List<Marmita>>
}