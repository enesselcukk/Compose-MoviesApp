package com.enesselcuk.moviesui.util

import com.enesselcuk.moviesui.data.remote.MoviesService
import com.enesselcuk.moviesui.data.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.domain.repository.Repos
import dagger.assisted.Assisted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        try {
            request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZDc3OWMxZTdhMjAyZDJhYTRlMjBlYjkwYTY3NTQ4ZSIsInN1YiI6IjYxYzQ5NmFhY2FlMTdjMDBjNjg3MWJhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.muSTAiNlUaJg92Pg06C6q2ImYmYjK0onTdPl-srkfIQ")
                .build()

        } catch (ex: Exception) {
            val exception = ex
        }
        return chain.proceed(request)
    }
}
