package com.enesselcuk.moviesui.util

import kotlinx.coroutines.flow.*

abstract class CustomNetwork {
    companion object {
        suspend fun <T> network(networkResult: NetworkResult<T>, stateFlow: MutableStateFlow<T>) {
            when (networkResult) {
                is NetworkResult.Error -> {

                }
                is NetworkResult.Success -> {
                    stateFlow.emit(networkResult.data)
                }
                is NetworkResult.Loading -> {

                }
            }
        }

        suspend inline fun <T> networkCall(crossinline call: suspend () -> T): Flow<NetworkResult<T>> = flow {
            try {
                emit(NetworkResult.Loading())
                val response = call.invoke()
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e))
            }
        }

        /*
        fun<T> result(call: suspend() -> Response<T>) : Flow<NetworkResult<T?>> = flow {
            emit(NetworkResult.Loading())

            try {
                val c = call()
                c.let {
                    if (c.isSuccessful){
                        val cookie=c.headers()["Set-Cookie"]
                        emit(NetworkResult.Success(c.body(),cookie?:""))
                    }else{
                        c.errorBody()?.let {
                            it.close()
                            emit(ApiResponse.failure(Error(it.toString()), Response.error(400,c.errorBody())))

                        }

                    }
                }
            }catch(t:Throwable){
                t.printStackTrace()
                emit(ApiResponse.failure(t,null))
            }
        }
         */

    }

}