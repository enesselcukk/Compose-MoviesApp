package com.enesselcuk.moviesui.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStoreImpl @Inject constructor(private val context: Context) : LocalDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Preferences_dataStore")
    override suspend fun remove(key: String) {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        val dataStoreUsersKey = stringSetPreferencesKey(key)
        context.dataStore.edit {
            when(key){
                dataStoreDarkKey.name -> {
                    it.remove(dataStoreDarkKey)
                }
                dataStoreUsersKey.name -> {
                    it.remove(dataStoreUsersKey)
                }
            }
        }
    }

    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreDarkKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreDarkKey]
        }.firstOrNull()
    }

    override suspend fun setUsers(key: String, vararg user: String) {
        val dataStoreUsersKey = stringSetPreferencesKey(key)
        context.dataStore.edit {
            it[dataStoreUsersKey] = user.toSet()
        }
    }

    override suspend fun getUsers(key: String): Set<String>? {
        val dataStoreUsersKey = stringSetPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[dataStoreUsersKey]
        }.firstOrNull()
    }
}