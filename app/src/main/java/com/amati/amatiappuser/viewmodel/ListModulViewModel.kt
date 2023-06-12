package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.AllModulItem
import com.amati.amatiappuser.network.response.ListModulResponse
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListModulViewModel : ViewModel() {
    private val _dataListModul = MutableLiveData<List<AllModulItem>>()
    val dataListModul: LiveData<List<AllModulItem>> = _dataListModul

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun getListModul(token: String, id: Int) {
        val client = ApiConfig.getApiService().getListModul(token, id)
        client.enqueue(object : Callback<ListModulResponse> {
            override fun onResponse(
                call: Call<ListModulResponse>,
                response: Response<ListModulResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataListModul.value = responseBody.allModul
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<ListModulResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}