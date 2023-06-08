package com.amati.amatiappuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiappuser.network.response.ProfilResponse
import com.amati.amatiappuser.network.response.RequestProfil
import com.amati.amatiappuser.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilViewModel: ViewModel() {
    private val _dataUser = MutableLiveData<ProfilResponse>()
    val dataUser: LiveData<ProfilResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    fun edit(requestProfil: RequestProfil) {
        val client = ApiConfig.getApiService().profil(requestProfil)
        client.enqueue(object : Callback<ProfilResponse> {
            override fun onResponse(
                call: Call<ProfilResponse>,
                response: Response<ProfilResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = responseBody!!
                        _code.value = responseBody.code
                    }
                } else {
                    _code.value = response.code()
                }
            }

            override fun onFailure(call: Call<ProfilResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}