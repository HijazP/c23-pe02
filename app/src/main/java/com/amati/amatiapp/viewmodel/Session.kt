package com.amati.amatiapp.viewmodel

import androidx.lifecycle.*
import com.amati.amatiapp.database.UserPreferencesDatastore
import kotlinx.coroutines.launch

class Session(private val pref : UserPreferencesDatastore) : ViewModel(){

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun setSession(name: String, id: Int, token: String) {
        viewModelScope.launch {
            pref.storeUser(name, id, token)
        }
    }

    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}
