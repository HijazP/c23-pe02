package com.amati.amatiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amati.amatiapp.network.response.DesaItem
import com.amati.amatiapp.network.response.GetProfilResponse
import com.amati.amatiapp.network.response.ProfilResponse
import com.amati.amatiapp.network.retrofit.ApiConfig
import com.amati.amatiapp.response.RequestProfil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilViewModel: ViewModel() {
    private val _dataUser = MutableLiveData<ProfilResponse>()
    val dataUser: LiveData<ProfilResponse> = _dataUser

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code

    private val _dataProfil = MutableLiveData<List<DesaItem>>()
    val dataProfil:  LiveData<List<DesaItem>> = _dataProfil

    private val _message = MutableLiveData<String>()
    val msg: LiveData<String> = _message

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

    fun getProfil(token: String){
        val client = ApiConfig.getApiService().getProfil(token)
        client.enqueue(object : Callback<GetProfilResponse> {
            override fun onResponse(
                call: Call<GetProfilResponse>,
                response: Response<GetProfilResponse>
            ) {
                val responseBody =  response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _message.value = responseBody.message
                        _dataProfil.value = responseBody.desa
                        _code.value = responseBody.statusCode
                    }
                } else {
                    _code.value = responseBody?.statusCode
                }
            }

            override fun onFailure(call: Call<GetProfilResponse>, t: Throwable) {
                _code.value = 500
            }
        })
    }
}