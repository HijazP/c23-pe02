package com.amati.amatiapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiapp.network.RequestLogin

class LoginViewModel: ViewModel(){
//    private val _dataUser = MutableLiveData<LoginResponse>()
//    val dataUser: LiveData<LoginResponse> = _dataUser

    fun login(context: Context, requestLogin: RequestLogin) {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().login(requestLogin)
//        client.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(
//                call: Call<LoginResponse>,
//                response: Response<LoginResponse>
//            ) {
//                _isLoading.value = false
//                val responseBody = response.body()
//                if (response.isSuccessful) {
//                    if (responseBody != null) {
//                        isError = responseBody.error
//                        _dataUser.value = responseBody!!
//                        _message.value = responseBody.message
//                    }
//                } else{
//                    isError = true
//                    _message.value = response.message()
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
//                _message.value = t.message.toString()
//                Toast.makeText(context, "onFailure: ${_message.value}", Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}