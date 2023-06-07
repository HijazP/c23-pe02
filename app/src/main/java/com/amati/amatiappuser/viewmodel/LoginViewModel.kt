package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.*
import com.amati.amatiappuser.network.response.LoginResponse
import com.amati.amatiappuser.network.response.RequestLogin
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel(){
    private val _dataUser = MutableLiveData<LoginResponse>()
    val dataUser: LiveData<LoginResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun login(requestLogin: RequestLogin) {
//        _isLoading.value = true
        val client = ApiConfig.getApiService().login(requestLogin)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
//                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = responseBody!!
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
                _code.value = 500
//                Toast.makeText(context, "onFailure: ${_message.value}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}