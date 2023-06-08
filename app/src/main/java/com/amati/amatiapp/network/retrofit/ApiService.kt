package com.amati.amatiapp.network.retrofit

import com.amati.amatiapp.network.response.*
import com.amati.amatiapp.response.RequestLogin
import com.amati.amatiapp.response.RequestProblem
import com.amati.amatiapp.response.RequestProfil
import com.amati.amatiapp.response.RequestReg
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("desa/login")
    fun login(
        @Body
        requestLogin: RequestLogin
    ): Call<LoginResponse>

    @POST("desa/register")
    fun register(
        @Body
        RequestReg: RequestReg
    ): Call<RegisterResponse>

    @POST("desa/register")
    fun profil(
        @Body
        requestProfil: RequestProfil
    ): Call<ProfilResponse>

    @GET("desa/problem")
    fun getProblem(
        @Header("Authorization") token: String
    ): Call<GetProblemResponse>

    @POST("desa/problem")
    fun add(
        @Body
        requestProblem: RequestProblem
    ): Call<ProblemResponse>

    @GET("/desa")
    fun getProfil(
        @Header("Authorization") token: String
    ): Call<GetProfilResponse>
}


