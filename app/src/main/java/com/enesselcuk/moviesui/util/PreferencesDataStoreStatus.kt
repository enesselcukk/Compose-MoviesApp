package com.enesselcuk.moviesui.util

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.util.Locale


object PreferencesDataStoreStatus {

    private const val DARK_STATUS = "darkStatus"
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DARK_STATUS)
    var dataStoreDarkKey = booleanPreferencesKey("dark")
    suspend fun status(context: Context, status: Boolean) {
        context.dataStore.edit { it[dataStoreDarkKey] = status }
    }



    private const val LANGUAGECHOOSE = "language"
    val Context.dataStoreLanguage: DataStore<Preferences> by preferencesDataStore(LANGUAGECHOOSE)
    var dataStoreLangKey = stringPreferencesKey("lang")

    suspend fun setLanguage(context: Context, lang: String) {
        context.dataStoreLanguage.edit { it[dataStoreLangKey] = lang }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun updateLanguage(context: Context, language: String): Context {
        var context = context

        var locale = Locale(language)
        var conf = context.resources.configuration
        var metrics = context.resources.displayMetrics
        if (Build.VERSION.SDK_INT > 24) {
            var localeList = LocaleList(locale)
            conf.setLocales(localeList)

            context.resources.updateConfiguration(conf, metrics)
        } else {
            conf.setLocale(locale)
            context.resources.updateConfiguration(conf, metrics)
        }
        return context
    }
}
