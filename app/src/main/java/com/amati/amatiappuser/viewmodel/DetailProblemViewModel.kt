package com.amati.amatiappuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.DetailMasalahItem
import com.amati.amatiappuser.network.response.DetailProblemResponse
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProblemViewModel : ViewModel() {
    private val _dataDetail = MutableLiveData<DetailProblemResponse?>()
    val dataDetail: MutableLiveData<DetailProblemResponse?> = _dataDetail

    private val _code = MutableLiveData<Int?>()
    val code: MutableLiveData<Int?> = _code

    fun displayDetail(token: String, id: String) {
        val client = ApiConfig.getApiService().displayDetail(token, id)
        client.enqueue(object: Callback<DetailProblemResponse> {
            override fun onResponse(call: Call<DetailProblemResponse>, response: Response<DetailProblemResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDetail.value = responseBody
                        _code.value = responseBody.statusCode
                    }
                    else {
                        _code.value = response.code()
                        Log.e(TAG, "onFailure: ${response.message()}")
                        Log.d(TAG, response.toString())
                    }
                }
            }

            override fun onFailure(call: Call<DetailProblemResponse>, t: Throwable) {
                _code.value = 500
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailProblemViewModel"
    }
}