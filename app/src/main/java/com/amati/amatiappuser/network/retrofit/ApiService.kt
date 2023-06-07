package com.amati.amatiappuser.network.retrofit

import com.amati.amatiappuser.network.response.LoginResponse
import com.amati.amatiappuser.network.response.RegisterResponse
import com.amati.amatiappuser.network.response.RequestLogin
import com.amati.amatiappuser.network.response.RequestReg
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("desa/login")
    fun login(
        @Body
        requestLogin: RequestLogin
    ): Call<LoginResponse>

    @POST("api/v1/register")
    fun register(
        @Body
        userRequestReg: RequestReg
    ): Call<RegisterResponse>
}


