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

    private val _dataModul = MutableLiveData<Modul>()
    val dataModul: LiveData<Modul> = _dataModul

    private val _dataDetailModul = MutableLiveData<Modul>()
    val dataDetailModul: LiveData<Modul> = _dataDetailModul

    private val _dataProgressCourse = MutableLiveData<AmbilKursus>()
    val dataProgressCourse: LiveData<AmbilKursus> = _dataProgressCourse

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

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
                        _dataCourse.value = responseBody.ambilKursus
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

    fun progressCourse(token: String, id: Int, status: Boolean){
        val client = ApiConfig.getApiService().putProgress(token, id, status)
        client.enqueue(object : Callback<GetCourseResponse> {
            override fun onResponse(
                call: Call<GetCourseResponse>,
                response: Response<GetCourseResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataProgressCourse.value = responseBody.ambilKursus
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

    fun detailModul(token: String, idModul: Int? = null){
        val client = ApiConfig.getApiService().getDetailModul(token, idModul)
        client.enqueue(object : Callback<GetCourseResponse> {
            override fun onResponse(
                call: Call<GetCourseResponse>,
                response: Response<GetCourseResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataDetailModul.value = responseBody.modul
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

//    fun getDetailCourse(token: String, id: Int){
//        val client = ApiConfig.getApiService().getCourseDetail(token, id)
//        client.enqueue(object : Callback<GetDetailCourseResponse> {
//            override fun onResponse(
//                call: Call<GetDetailCourseResponse>,
//                response: Response<GetDetailCourseResponse>
//            ) {
//                val responseBody = response.body()
//                if (response.isSuccessful) {
//                    if (responseBody != null) {
//                        _dataDetailCourse.value = responseBody.kursus
//                        _code.value = responseBody.statusCode
//                    }
//                } else{
//                    _code.value = response.code()
//                }
//            }
//
//            override fun onFailure(call: Call<GetDetailCourseResponse>, t: Throwable) {
//                _code.value = 500
//            }
//        })
//    }
}