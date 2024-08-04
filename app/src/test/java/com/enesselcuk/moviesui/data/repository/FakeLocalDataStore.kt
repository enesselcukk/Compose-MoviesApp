package com.enesselcuk.moviesui.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.enesselcuk.moviesui.datastore.LocalDataStore

class FakeLocalDataStore(private val context: Context): LocalDataStore {

    override suspend fun remove(key: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getBoolean(key: String): Boolean? {
        TODO("Not yet implemented")
    }

    override suspend fun setUsers(key: String, vararg user: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(key: String): Set<String>? {
        TODO("Not yet implemented")
    }
}