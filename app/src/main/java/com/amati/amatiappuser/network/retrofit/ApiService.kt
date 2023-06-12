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

    @GET("user/course")
    fun getCourse(
        @Header("Authorization") token: String
    ): Call<GetAllCourseResponse>

    @GET("user/course/{id}")
    fun getCoursebyId(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<GetCourseByIdResponse>

    @GET("user/course/module")
    fun getDetailModul(
        @Header("Authorization") token: String,
        @Query("idModul") idModul: Int
    ): Call<GetCourseByIdResponse>

    @GET("user/course/progress")
    fun getProgressCourse(
        @Header("Authorization") token: String
    ): Call<GetAllProgressResponse>

    @PUT("user/course")
    fun putProgress(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
        @Query("status") status: String
    ): Call<PutProgressResponse>

    @GET("user/course/{id}/detail")
    fun getCourseDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<GetDetailCourseResponse>

    @POST("user/course/recommendation/list")
    fun getDesaku(
        @Header("Authorization") token: String
    ): Call<GetDesakuResponse>

    @GET("desa/problem/{id}")
    fun displayDetail(
        @Header("Authorization") bearer: String,
        @Path("id") id: String
    ): Call<DetailProblemResponse>

    @GET("user/course/module")
    fun getListModul(
        @Header("Authorization") token: String,
        @Query("idKursus") idKursus: Int
    ): Call<ListModulResponse>
}


