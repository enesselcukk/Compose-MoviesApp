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
}