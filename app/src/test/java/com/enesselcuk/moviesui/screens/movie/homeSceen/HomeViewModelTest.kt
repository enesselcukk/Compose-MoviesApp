package com.enesselcuk.moviesui.screens.movie.homeSceen

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.enesselcuk.moviesui.TestCoroutineRule
import com.enesselcuk.moviesui.data.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.Result
import com.enesselcuk.moviesui.data.remote.MoviesService
import com.enesselcuk.moviesui.domain.useCase.home.HomeTrendUseCase
import com.enesselcuk.moviesui.domain.useCase.home.HomeUseCase
import com.enesselcuk.moviesui.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mainRepos: RepositoryImpl

    @Mock
    lateinit var homeUseCase: HomeUseCase

    @Mock
    lateinit var homeTrendUseCase: HomeTrendUseCase

    lateinit var homeViewModel: HomeViewModel

    lateinit var service: MoviesService

    @Before
    fun setUp() {
        service = mockk()
        MockKAnnotations.init(this)
        mainRepos = RepositoryImpl(service)
        homeUseCase = HomeUseCase(mainRepos)
        homeTrendUseCase = HomeTrendUseCase(mainRepos)
        homeViewModel = HomeViewModel(homeUseCase,homeTrendUseCase)
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


