package com.amati.amatiappuser.network.retrofit

import com.amati.amatiappuser.network.response.*
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

    @POST("desa/register")
    fun profil(
        @Body
        requestProfil: RequestProfil
    ): Call<ProfilResponse>
}


