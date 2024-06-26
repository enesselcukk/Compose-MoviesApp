package com.enesselcuk.moviesui.domain.useCase.actor

import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl
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
class ActorMoviesUseCaseTest {

    @Mock private lateinit var actorMoviesUseCase: ActorMoviesUseCase

    @Mock private lateinit var fakeRepositoryImpl: FakeRemoteRepositoryImpl

    @Before
    fun setUp(){
        fakeRepositoryImpl = FakeRemoteRepositoryImpl()
        actorMoviesUseCase = ActorMoviesUseCase(fakeRepositoryImpl)
    }
    @Test
    operator fun invoke() = runTest{
        val fakeResponse = actorMoviesUseCase.invoke(1,"tr").first()

        when(fakeResponse){
            is NetworkResult.Success -> {
                //then
                assertEquals(FakeRemoteRepositoryImpl.fakeActorMoviesResponse,fakeResponse.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }
}