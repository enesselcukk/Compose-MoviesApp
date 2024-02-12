package com.enesselcuk.moviesui.data.remote


import com.enesselcuk.moviesui.unit.enqueueResponse
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.remote.MoviesService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var moviesService: MoviesService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

         val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        moviesService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MoviesService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMovies() = runBlocking{
        // Given
        mockWebServer.enqueueResponse("moviesResponse.json")

        // When
        val response = moviesService.getMovies("popular", language = "en-US", page = 1)
        val request = mockWebServer.takeRequest()

        // Then
        assertThat(response,instanceOf(MoviesResponse::class.java))
        assertThat(response.results[0].title, `is`("Meg 2: The Trench"))
        assertThat(response.results.size, `is`(20))
        assertThat(request.path,`is`("/3/movie/popular?api_key=7d779c1e7a202d2aa4e20eb90a67548e&language=en-US&page=1"))
        assertThat(request.method,`is`("GET"))

    }
}