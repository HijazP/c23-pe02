package com.amati.amatiapp.network.retrofit

import com.amati.amatiapp.databinding.ActivityLoginBinding
import com.amati.amatiapp.network.RequestLogin
import com.amati.amatiapp.network.RequestReg
import com.amati.amatiapp.network.response.Response
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {
    @POST("api/v1/login")
    fun login(
        @Body userRequestLogin: RequestLogin
    ): Call<Response>

    @POST("api/v1/register")
    fun register(
        @Body userRequestReg: RequestReg
    ): Call<Response>
}


