package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.*
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseViewModel: ViewModel(){
    private val _dataCourse = MutableLiveData<AmbilKursus>()
    val dataCourse: LiveData<AmbilKursus> = _dataCourse

    private val _dataDetailModul = MutableLiveData<Modul>()
    val dataDetailModul: LiveData<Modul> = _dataDetailModul

    private val _dataProgressCourse = MutableLiveData<PutProgressResponse>()
    val dataProgressCourse: LiveData<PutProgressResponse> = _dataProgressCourse

    private val _dataDetailCourse = MutableLiveData<Kursus>()
    val dataDetailCourse: LiveData<Kursus> = _dataDetailCourse

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun getCourseById(token: String, id: Int) {
        val client = ApiConfig.getApiService().getCoursebyId(token, id)
        client.enqueue(object : Callback<GetCourseByIdResponse> {
            override fun onResponse(
                call: Call<GetCourseByIdResponse>,
                response: Response<GetCourseByIdResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataCourse.value = responseBody.ambilKursus!!
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetCourseByIdResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }

    fun putProgressCourse(token: String, id: Int, status: String){
        val client = ApiConfig.getApiService().putProgress(token, id, status)
        client.enqueue(object : Callback<PutProgressResponse> {
            override fun onResponse(
                call: Call<PutProgressResponse>,
                response: Response<PutProgressResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataProgressCourse.value = response.body()
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<PutProgressResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }

    fun getDetailModul(token: String, idModul: Int){
        val client = ApiConfig.getApiService().getDetailModul(token, idModul)
        client.enqueue(object : Callback<GetCourseByIdResponse> {
            override fun onResponse(
                call: Call<GetCourseByIdResponse>,
                response: Response<GetCourseByIdResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDetailModul.value = responseBody.modul!!
                        _code.value = responseBody.statusCode
                    }
                } else{
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<GetCourseByIdResponse>, t: Throwable) {
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
}