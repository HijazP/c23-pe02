package com.amati.amatiapp.network.retrofit

import com.amati.amatiapp.network.response.*
import com.amati.amatiapp.response.*
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
        @Header("Authorization") bearer: String,
        @Body requestProblem: RequestProblem
    ): Call<ProblemResponse>

    @GET("desa/problem/{id}")
    fun displayDetail(
        @Header("Authorization") bearer: String,
        @Path("id") id: String
    ): Call<DetailResponse>

    @GET("/desa")
    fun getProfil(
        @Header("Authorization") token: String
    ): Call<GetProfilResponse>
}


