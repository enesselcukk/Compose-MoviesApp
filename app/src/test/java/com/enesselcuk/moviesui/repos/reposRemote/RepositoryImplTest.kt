package com.enesselcuk.moviesui.repos.reposRemote

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.enesselcuk.moviesui.TestCoroutineRule
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.source.model.response.Result
import com.enesselcuk.moviesui.source.remote.MoviesService
import com.enesselcuk.moviesui.util.NetworkResult
import io.grpc.internal.SharedResourceHolder.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


@ExperimentalCoroutinesApi
class RepositoryImplTest {


    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = TestCoroutineRule()

    // Mock dependencies
    @Mock
    private lateinit var mockRemoteDataSource: MoviesService

    // Create a RepositoryImpl instance
    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
        mockRemoteDataSource = mockk()
        MockKAnnotations.init(this)
        repository = RepositoryImpl(mockRemoteDataSource)
    }


    @Test
    fun testGetMoviesSuccess() = runTest {
        // Given
        val mockMoviesService = mockk<MoviesService>()
        coEvery {
            mockMoviesService.getMovies(
                title = "popular",
                language = "en-Us",
                page = 1
            )
        } coAnswers {
            delay(1000)
            createFakeGameItemList()[0]
        }

        // When
        val repos = RepositoryImpl(mockMoviesService)
        val result = repos.getMovies("popular", language = "en-Us", page = 1)

        // Then
        val loadingResult = result.first()
        assertThat(loadingResult, `is`(NetworkResult.Loading()))
        val successResult = result.drop(1).first()
        assertThat(successResult, `is`(NetworkResult.Success(createFakeGameItemList()[0])))

    }

    private fun createFakeGameItemList() = listOf<MoviesResponse>(
        MoviesResponse(
            1, 1, listOf(
                Result(
                    1, true, "test", listOf(1),
                    "testname", "test", "test", 8.5,
                    "test", "test", "test", true, 1.5, 5
                )
            ), 1, 1
        ),
        MoviesResponse(
            1, 1, listOf(
                Result(
                    1, true, "test", listOf(1),
                    "testname", "test", "test", 8.5,
                    "test", "test", "test", true, 1.5, 5
                )
            ), 1, 1
        ),
        MoviesResponse(
            1, 1, listOf(
                Result(
                    1, true, "test", listOf(1),
                    "testname", "test", "test", 8.5,
                    "test", "test", "test", true, 1.5, 5
                )
            ), 1, 1
        ),
        MoviesResponse(
            1, 1, listOf(
                Result(
                    1, true, "test", listOf(1),
                    "testname", "test", "test", 8.5,
                    "test", "test", "test", true, 1.5, 5
                )
            ), 1, 1
        ),
        MoviesResponse(
            1, 1, listOf(
                Result(
                    1, true, "test", listOf(1),
                    "testname", "test", "test", 8.5,
                    "test", "test", "test", true, 1.5, 5
                )
            ), 1, 1
        ),
    )


}
