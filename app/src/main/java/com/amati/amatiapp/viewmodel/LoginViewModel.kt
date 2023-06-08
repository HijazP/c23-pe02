package com.amati.amatiapp.viewmodel

import androidx.lifecycle.*
import com.amati.amatiapp.network.response.LoginResponse
import com.amati.amatiapp.network.retrofit.ApiConfig
import com.amati.amatiapp.response.RequestLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel(){
    private val _dataUser = MutableLiveData<LoginResponse>()
    val dataUser: LiveData<LoginResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun login(requestLogin: RequestLogin) {
        val client = ApiConfig.getApiService().login(requestLogin)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = response.body()!!
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}