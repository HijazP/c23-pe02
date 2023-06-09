package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.RegisterResponse
import com.amati.amatiappuser.network.response.RequestReg
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val _dataUser = MutableLiveData<RegisterResponse>()
    val dataUser: LiveData<RegisterResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun register(requestReg: RequestReg) {
        val client = ApiConfig.getApiService().register(requestReg)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = response.body()
                        _code.value = responseBody.statusCode
                    }
                } else {
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}