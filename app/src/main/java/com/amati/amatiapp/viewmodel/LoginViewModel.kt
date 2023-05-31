package com.amati.amatiapp.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.amati.amatiapp.database.UserPreferencesDatastore
import com.amati.amatiapp.network.response.LoginResponse
import com.amati.amatiapp.network.retrofit.ApiConfig
import com.amati.amatiapp.response.RequestLogin
import com.amati.amatiapp.ui.LoginActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel(){
    private val pref = UserPreferencesDatastore(context = LoginActivity())

    private val _dataUser = MutableLiveData<LoginResponse>()
    val dataUser: LiveData<LoginResponse> = _dataUser

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun setSession(name: String, id: Int, token: String) {
        viewModelScope.launch {
            pref.storeUser(name, id, token)
        }
    }


    fun login(context: Context, requestLogin: RequestLogin) {
//        _isLoading.value = true
        val client = ApiConfig.getApiService().login(requestLogin)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
//                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody != null) {
                        _dataUser.value = responseBody!!
                    }
                } else{
//                    isError = true
//                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
//                _message.value = t.message.toString()
//                Toast.makeText(context, "onFailure: ${_message.value}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}