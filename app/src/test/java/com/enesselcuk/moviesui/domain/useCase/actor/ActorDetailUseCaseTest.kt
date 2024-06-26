package com.enesselcuk.moviesui.domain.useCase.actor

import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl
import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl.MoviesList.fakeActorDetailResponse
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ActorDetailUseCaseTest{

    @Mock
    private lateinit var actorDetailUseCase : ActorDetailUseCase

    @Mock private lateinit var fakeRepositoryImpl: FakeRemoteRepositoryImpl

    @Before
    fun setUp(){
        fakeRepositoryImpl = FakeRemoteRepositoryImpl()
        actorDetailUseCase = ActorDetailUseCase(fakeRepositoryImpl)
    }


    @Test
    fun getActorMovies()= runTest{
        // give
        val response = actorDetailUseCase.invoke(1,"language").first()

        // when
        when(response){
            is NetworkResult.Success -> {
                //then
                assertEquals(fakeActorDetailResponse,response.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }
}