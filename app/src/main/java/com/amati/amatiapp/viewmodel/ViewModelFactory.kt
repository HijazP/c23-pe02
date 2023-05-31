package com.amati.amatiapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amati.amatiapp.database.UserPreferencesDatastore

@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel() as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}