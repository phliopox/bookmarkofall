package com.ph.bookmarkofall.data.common

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="f")

class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val appContext = context.applicationContext

    val firstActivate : Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[FIRST_ACTIVATE]
        }

    suspend fun isNotFirstActivate(){
        appContext.dataStore.edit { pref->
            pref[FIRST_ACTIVATE] = "false"
        }
    }
    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")
        private val RECENT_SEARCH_TERM = stringPreferencesKey("recent_search_term")
        private val FIRST_ACTIVATE = stringPreferencesKey("first_activate")

    }
}
