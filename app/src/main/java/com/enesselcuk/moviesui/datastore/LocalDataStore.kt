package com.enesselcuk.moviesui.datastore

interface LocalDataStore {
   suspend fun putBoolean(key: String,value:Boolean)
   suspend fun getBoolean(key:String):Boolean?
}