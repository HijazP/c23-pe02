package com.amati.amatiappuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProblemViewModel : ViewModel() {
    private val _dataDetail = MutableLiveData<DetailProblemResponse?>()
    val dataDetail: MutableLiveData<DetailProblemResponse?> = _dataDetail

    private val _dataDesa = MutableLiveData<List<RecDesaItem>>()
    val dataDesa: LiveData<List<RecDesaItem>> = _dataDesa

    private val _problemDesa = MutableLiveData<List<MasalahdesaItem>>()
    val problemDesa: LiveData<List<MasalahdesaItem>> = _problemDesa

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

    fun getRekomendasiMasalah(token: String, namaDesa: String){
        val client = ApiConfig.getApiService().getUserRekomendasi(token, namaDesa)
        client.enqueue(object : Callback<RekomendasiMasalahResponse> {
            override fun onResponse(
                call: Call<RekomendasiMasalahResponse>,
                response: Response<RekomendasiMasalahResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDesa.value = responseBody.recDesa
                        _problemDesa.value = responseBody.masalahdesa
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<RekomendasiMasalahResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }

    companion object {
        private const val TAG = "DetailProblemViewModel"
    }
}