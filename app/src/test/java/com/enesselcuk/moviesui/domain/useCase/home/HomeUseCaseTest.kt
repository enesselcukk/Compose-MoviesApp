package com.enesselcuk.moviesui.domain.useCase.home

import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl
import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl.MoviesList.fakeMoviesResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeUseCaseTest{


    @Mock
    private lateinit var fakeRepos : FakeRemoteRepositoryImpl

    @Mock
    private lateinit var homeUseCase: HomeUseCase

    @Before
    fun setUp(){
        fakeRepos = FakeRemoteRepositoryImpl()
        homeUseCase = HomeUseCase(fakeRepos)
    }

    @Test
    fun getHome()= runTest{
        // given
        val movies = homeUseCase.invoke("title","tr",1).first()
        val moviesList = fakeMoviesResponse

        // when
        when(movies){
            is NetworkResult.Success -> {
                // then
               assertEquals(moviesList,movies.data)
            }

            is NetworkResult.Error -> TODO()
            is NetworkResult.Loading -> TODO()
        }

    }
}