package com.amati.amatiapp.database

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class UserPreferencesDatastore(context: Context) {
    private val Context.dataStore by preferencesDataStore("user_pref")
    private val dataStore = context.dataStore

    companion object {
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun storeUser(name: String, id: String, token: String) {
        dataStore.edit { pref ->
            pref[USER_NAME] = name
            pref[USER_ID] = id
            pref[USER_TOKEN] = token
        }
    }
    // btw dari sini bisa lgsng diambil data usernya ngga sih? kek nya musti bikin fungsi baru buat ambil data

    suspend fun logout() {
        dataStore.edit { pref ->
            pref[USER_NAME] = ""
            pref[USER_ID] = ""
            pref[USER_TOKEN] = ""
        }
    }

//  fun getUser(): Flow< >{
//      return dataStore.data.map{ pref ->
//      }
//   } mungkin pake ini buat ambil datanya

}