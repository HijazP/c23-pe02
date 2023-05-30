package com.amati.amatiapp.network

import com.amati.amatiapp.response.LoginResponse
import com.amati.amatiapp.response.RequestLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
     fun login(
         @Body
         requestLogin: RequestLogin
     ): Call<LoginResponse>

//     @POST("register")
//     fun register(
//         @Body
//         requestReg: RequestReg
//     ): Call<RegisterResponse>
}