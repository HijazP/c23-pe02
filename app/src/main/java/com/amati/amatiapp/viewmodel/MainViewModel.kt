package com.amati.amatiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiapp.network.response.GetProblemResponse
import com.amati.amatiapp.network.response.MasalahItem
import com.amati.amatiapp.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _dataProblem = MutableLiveData<List<MasalahItem>>()
    val dataProblem: LiveData<List<MasalahItem>> = _dataProblem

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    private val _message = MutableLiveData<String>()
    val msg: LiveData<String> = _message

    fun getProblem(token: String) {
//        _isLoading.value = true
        val client = ApiConfig.getApiService().getProblem(token)
        client.enqueue(object : Callback<GetProblemResponse> {
            override fun onResponse(
                call: Call<GetProblemResponse>,
                response: Response<GetProblemResponse>
            ) {
//                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _message.value = responseBody.totalMasalah.toString()
                        _dataProblem.value = responseBody.masalah
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = responseBody?.statusCode
                }
            }

            override fun onFailure(call: Call<GetProblemResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
                _code.value = 500
//                Toast.makeText(context, "onFailure: ${_message.value}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}