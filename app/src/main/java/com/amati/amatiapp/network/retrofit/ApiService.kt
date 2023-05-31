package com.amati.amatiapp.network.retrofit

import com.amati.amatiapp.network.response.LoginResponse
import com.amati.amatiapp.network.response.RegisterResponse
import com.amati.amatiapp.response.RequestLogin
import com.amati.amatiapp.response.RequestReg
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(
        @Body
        requestLogin: RequestLogin
    ): Call<LoginResponse>

    @POST("register")
    fun register(
        @Body
        userRequestReg: RequestReg
    ): Call<RegisterResponse>
}


