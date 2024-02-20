package com.enesselcuk.moviesui.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStoreImpl @Inject constructor(private val context: Context):LocalDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Preferences_dataStore")

    override suspend fun putBoolean(key: String, value: Boolean) {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it.clear()
            it.remove(dataStoreDarkKey)
            it[dataStoreDarkKey] = value }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        return context.dataStore.data.map{ preferences ->
            preferences[dataStoreDarkKey]
        }.firstOrNull()
    }

    override suspend fun remove(key: String) {
        val dataStoreDarkKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it.remove(dataStoreDarkKey)
        }
    }

    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }

}