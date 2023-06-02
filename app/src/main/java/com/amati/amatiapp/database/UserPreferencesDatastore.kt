package com.amati.amatiapp.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesDatastore private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun storeUser(name: String, id: Int, token: String) {
        dataStore.edit { pref ->
            pref[USER_NAME] = name
            pref[USER_ID] = id
            pref[USER_TOKEN] = token
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { pref ->
            pref[USER_TOKEN] ?: ""
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.map { pref ->
            pref[USER_NAME] ?: ""
        }
    }

    suspend fun logout() {
        dataStore.edit { pref ->
            pref[USER_NAME] = ""
            pref[USER_ID] = 0
            pref[USER_TOKEN] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferencesDatastore? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferencesDatastore {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferencesDatastore(dataStore)
                INSTANCE = instance
                instance
            }
        }

        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_TOKEN = stringPreferencesKey("user_token")
    }
}