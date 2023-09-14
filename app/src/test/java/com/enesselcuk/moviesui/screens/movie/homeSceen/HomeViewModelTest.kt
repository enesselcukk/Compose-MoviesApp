package com.enesselcuk.moviesui.screens.movie.homeSceen

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.cash.turbine.test
import com.enesselcuk.moviesui.TestCoroutineRule
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.source.model.response.Result
import com.enesselcuk.moviesui.source.remote.MoviesService
import com.enesselcuk.moviesui.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mainRepos: RepositoryImpl

    lateinit var homeViewModel: HomeViewModel

    lateinit var service: MoviesService

    @Before
    fun setUp() {
        service = mockk()
        MockKAnnotations.init(this)
        mainRepos = RepositoryImpl(service)
        homeViewModel = HomeViewModel(mainRepos)
    }


    @Test
    fun getMovies() = runTest {
        // Given
        mainRepos = RepositoryImpl(service)

       val job = launch {
           homeViewModel.getMoviesFlow.collect()
       }

        coEvery { mainRepos.getMovies("popular", "en-US", 1) } returns flow {
            emit(NetworkResult.Success(createFakeGameItemList()[0]))
        }

        // when
        homeViewModel.getMovies("popular", "en-US", 1)

        //then
        assertThat(listOf(homeViewModel.getMoviesFlow.value), `is`(emptyList()))
        assertFalse(homeViewModel.loading.value ?: false)
        assertTrue(homeViewModel.isVisible.value)

        job.cancel()
    }

    private fun createFakeGameItemList() = listOf<MoviesResponse>(
        MoviesResponse(1, 1, listOf(
            Result(
                1, true, "test", listOf(1),
                "testname", "test", "test", 8.5,
                "test", "test", "test", true, 1.5, 5
            )
        ), 1, 1),
        MoviesResponse(1, 1, listOf(
            Result(
                1, true, "test", listOf(1),
                "testname", "test", "test", 8.5,
                "test", "test", "test", true, 1.5, 5
            )
        ), 1, 1),
        MoviesResponse(1, 1, listOf(
            Result(
                1, true, "test", listOf(1),
                "testname", "test", "test", 8.5,
                "test", "test", "test", true, 1.5, 5
            )
        ), 1, 1),
        MoviesResponse(1, 1, listOf(
            Result(
                1, true, "test", listOf(1),
                "testname", "test", "test", 8.5,
                "test", "test", "test", true, 1.5, 5
            )
        ), 1, 1),
        MoviesResponse(1, 1, listOf(
            Result(
                1, true, "test", listOf(1),
                "testname", "test", "test", 8.5,
                "test", "test", "test", true, 1.5, 5
            )
        ), 1, 1),
    )


}


