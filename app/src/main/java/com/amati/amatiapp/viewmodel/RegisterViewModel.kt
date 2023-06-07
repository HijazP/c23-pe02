package com.amati.amatiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiapp.network.response.RegisterResponse
import com.amati.amatiapp.network.response.RequestReg
import com.amati.amatiapp.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(): ViewModel() {
    private val _dataUser = MutableLiveData<RegisterResponse>()
    val dataUser: LiveData<RegisterResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun register(requestReg: RequestReg) {
//        _isLoading.value = true
        val client = ApiConfig.getApiService().register(requestReg)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
//                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = responseBody!!
                        _code.value = responseBody.code
                    }
                } else {
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
                _code.value = 500
//                Toast.makeText(context, "onFailure: ${_message.value}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}