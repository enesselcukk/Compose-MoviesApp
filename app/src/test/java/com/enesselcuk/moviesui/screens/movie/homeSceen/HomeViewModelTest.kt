package com.enesselcuk.moviesui.screens.movie.homeSceen

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.enesselcuk.moviesui.MainCoroutineRule
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class HomeViewModelTest{

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainRepos: RepositoryImpl

    @InjectMocks
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMovies()= mainCoroutineRule.dispatcher.runBlockingTest {
        // Given

        homeViewModel.getMoviesFlow.replayCache
    }


}