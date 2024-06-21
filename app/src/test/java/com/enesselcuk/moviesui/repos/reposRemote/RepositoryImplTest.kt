package com.enesselcuk.moviesui.repos.reposRemote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.enesselcuk.moviesui.TestCoroutineRule
import com.enesselcuk.moviesui.data.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.data.model.response.MoviesResponse
import com.enesselcuk.moviesui.data.model.response.Result
import com.enesselcuk.moviesui.data.remote.MoviesService
import com.enesselcuk.moviesui.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.Matchers.instanceOf
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
            createFakeGameItemList[0]
        }

        // When
        val repos = RepositoryImpl(mockMoviesService)
        val result = repos.getMovies("popular", language = "en-Us", page = 1)

        // Then
        val loadingResult = result.first()
        assertThat(loadingResult, `is`(NetworkResult.Loading()))
        val successResult = result.drop(1).first()
        assertThat(successResult, `is`(NetworkResult.Success(createFakeGameItemList[0])))

    }

    @Test
    fun testGetMoviesError() = runTest {
        //Given
        val title = "popular"
        val language = "en-US"
        val page = 1
        coEvery {
            mockRemoteDataSource.getMovies(
                title = title,
                language = language,
                page = page
            )
        } coAnswers {
            delay(1000)
            throw IllegalArgumentException("This is my error message")
        }

        // When
        val repos = RepositoryImpl(mockRemoteDataSource)
        val result = repos.getMovies(title, language = language, page = page)


        // Then
        val loadingResult = result.first()
        assertThat(loadingResult, `is`(NetworkResult.Loading()))


        val resultError = result.drop(1).first()
        assertThat(resultError, `is`(instanceOf(NetworkResult.Error::class.java)))

        if (resultError is NetworkResult.Error) {
            val errorMessage = resultError.message
            assertThat(errorMessage, `is`("This is my error message"))
        } else {
            fail("Expected NetworkResult.Error but got: $resultError")
        }
    }


    private val createFakeGameItemList = listOf<MoviesResponse>(
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
