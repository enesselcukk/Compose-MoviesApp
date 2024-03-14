package com.enesselcuk.moviesui.domain.useCase.datastore

import com.enesselcuk.moviesui.datastore.LocalDataStore
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(private val localDataStore: LocalDataStore) {
    suspend operator fun invoke(key:String): Boolean? {
       return localDataStore.getBoolean(key)
    }

    suspend operator fun invoke(key: String,value:Boolean){
        localDataStore.putBoolean(key, value)
    }

    suspend operator fun invoke(key: String,username: String,password:String,token:String){
        localDataStore.setUsers(key,username,password,token)
    }

    suspend fun clearUsers(key: String){
        localDataStore.remove(key)
    }

    suspend fun getUser(key: String):Set<String>?{
       return localDataStore.getUsers(key)
    }
}