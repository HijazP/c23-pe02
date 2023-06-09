package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val _dataAllCourse = MutableLiveData<List<KursusItem>>()
    val dataAllCourse: LiveData<List<KursusItem>> = _dataAllCourse

    private val _dataAllProgress = MutableLiveData<List<ProgressItem>>()
    val dataAllProgress: LiveData<List<ProgressItem>> = _dataAllProgress

    private val _dataDetailCourse = MutableLiveData<Kursus>()
    val dataDetailCourse: LiveData<Kursus> = _dataDetailCourse

    private val _dataModul = MutableLiveData<Modul>()
    val dataModul: LiveData<Modul> = _dataModul

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun getAllCourse(token: String) {
        val client = ApiConfig.getApiService().getCourse(token)
        client.enqueue(object : Callback<GetAllCourseResponse> {
            override fun onResponse(
                call: Call<GetAllCourseResponse>,
                response: Response<GetAllCourseResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataAllCourse.value = responseBody.kursus
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetAllCourseResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }

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

    fun getCourse(token: String, id: Int) {
        val client = ApiConfig.getApiService().getCoursebyId(token, id)
        client.enqueue(object : Callback<GetCourseResponse> {
            override fun onResponse(
                call: Call<GetCourseResponse>,
                response: Response<GetCourseResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataModul.value = responseBody.modul
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetCourseResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}