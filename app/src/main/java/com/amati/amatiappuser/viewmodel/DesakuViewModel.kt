package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DesakuViewModel: ViewModel(){

    private val _dataAllProgress = MutableLiveData<List<ProgressItem>>()
    val dataAllProgress: LiveData<List<ProgressItem>> = _dataAllProgress

    private val _dataDetailCourse = MutableLiveData<Kursus>()
    val dataDetailCourse: LiveData<Kursus> = _dataDetailCourse

    private val _dataNamaDesa = MutableLiveData<RekomendasiNamaDesaResponse>()
    val dataNamaDesa: LiveData<RekomendasiNamaDesaResponse> = _dataNamaDesa

    private val _dataDesa = MutableLiveData<List<RecDesaItem>>()
    val dataDesa: LiveData<List<RecDesaItem>> = _dataDesa

    private val _problemDesa = MutableLiveData<List<MasalahdesaItem>>()
    val problemDesa: LiveData<List<MasalahdesaItem>> = _problemDesa

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getAllProgress(token: String) {
        val client = ApiConfig.getApiService().getProgressCourse(token)
        client.enqueue(object : Callback<GetAllProgressResponse> {
            override fun onResponse(
                call: Call<GetAllProgressResponse>,
                response: Response<GetAllProgressResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataAllProgress.value = responseBody.progress
                        _code.value = responseBody.statusCode
                        _message.value = responseBody.message
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetAllProgressResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
    fun getDetailCourse(token: String, id: Int){
        val client = ApiConfig.getApiService().getCourseDetail(token, id)
        client.enqueue(object : Callback<GetDetailCourseResponse> {
            override fun onResponse(
                call: Call<GetDetailCourseResponse>,
                response: Response<GetDetailCourseResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDetailCourse.value = responseBody.kursus
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetDetailCourseResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }

    fun getNamaDesa(kursus: String){
        val client = ApiConfig.getApiService().prediksiKursus(kursus)
        client.enqueue(object : Callback<RekomendasiNamaDesaResponse> {
            override fun onResponse(
                call: Call<RekomendasiNamaDesaResponse>,
                response: Response<RekomendasiNamaDesaResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataNamaDesa.value = response.body()
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<RekomendasiNamaDesaResponse>, t: Throwable) {
                _code.value = 500
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
}