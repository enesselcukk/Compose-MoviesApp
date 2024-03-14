package com.enesselcuk.moviesui.datastore

interface LocalDataStore {

   suspend fun remove(key: String)

   suspend fun clear()
   suspend fun putBoolean(key: String,value:Boolean)
   suspend fun getBoolean(key:String):Boolean?

   suspend fun setUsers(key: String,vararg user:String)
   suspend fun getUsers(key: String):Set<String>?
}