package com.amati.amatiappuser.network.retrofit

import com.amati.amatiappuser.network.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun login(
        @Body
        requestLogin: RequestLogin
    ): Call<LoginResponse>

    @POST("user/register")
    fun register(
        @Body
        userRequestReg: RequestReg
    ): Call<RegisterResponse>

    @PUT("user/update")
    fun profil(
        @Body
        requestProfil: RequestProfil
    ): Call<ProfilResponse>

    @GET("user")
    fun getProfil(
        @Header("Authorization") token: String
    ): Call<GetProfilResponse>

    @PUT("user/course?id={id}&status={status}")
    fun putProgress(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Path("status") status: String
    ): Call<GetCourseResponse>

    @GET("user/course/{id}/detail")
    fun getCourse(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<GetDetailCourseResponse>
}


