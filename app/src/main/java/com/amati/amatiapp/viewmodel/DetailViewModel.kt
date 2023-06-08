package com.amati.amatiapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiapp.network.response.DetailResponse
import com.amati.amatiapp.network.response.ProblemResponse
import com.amati.amatiapp.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _dataDetail = MutableLiveData<DetailResponse>()
    val dataDetail: LiveData<DetailResponse> = _dataDetail

    private val _code = MutableLiveData<Int?>()
    val code: LiveData<Int?> = _code

    fun displayDetail(token: String, id: String) {
        val client = ApiConfig.getApiService().displayDetail(token, id)
        client.enqueue(object: Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDetail.value = response.body()
                        _code.value = responseBody.statusCode
                    }
                    else {
                        _code.value = response.code()
                        Log.e("AddProblemViewModel", "onFailure: ${response.message()}")
                        Log.d("AddProblemViewModel", response.toString())
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _code.value = 500
                Log.e("AddProblemViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}