package com.amati.amatiapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.network.response.ProblemResponse
import com.amati.amatiapp.network.retrofit.ApiConfig
import com.amati.amatiapp.response.RequestProblem
import com.amati.amatiapp.ui.dataStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProblemViewModel: ViewModel() {
    private val _dataProblem = MutableLiveData<ProblemResponse>()
    val dataProblem: LiveData<ProblemResponse> = _dataProblem

    private val _code = MutableLiveData<Int>()
    val code: LiveData<Int> = _code


    fun add(token: String, requestProblem: RequestProblem) {
        val client = ApiConfig.getApiService().add(token, requestProblem)
        client.enqueue(object: Callback<ProblemResponse> {
            override fun onResponse(call: Call<ProblemResponse>, response: Response<ProblemResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataProblem.value = response.body()
                        _code.value = responseBody.statusCode
                    } else {
                        _code.value = response.code()
                        Log.e("AddProblemViewModel", "onFailure: ${response.message()}")
                        Log.d("AddProblemViewModel", response.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ProblemResponse>, t: Throwable) {
                _code.value = 500
                Log.e("AddProblemViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }
}
