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
class ActorTvUseCaseTest {

    @Mock private lateinit var actorTvUseCase: ActorTvUseCase
    @Mock private lateinit var fakeRepositoryImpl: FakeRemoteRepositoryImpl


    @Before
    fun setUp(){
        fakeRepositoryImpl = FakeRemoteRepositoryImpl()
        actorTvUseCase = ActorTvUseCase(fakeRepositoryImpl)
    }
    @Test
    operator fun invoke() = runTest{
        //given
        val fakeResponse = actorTvUseCase.invoke(1,"tr").first()

        //when
        when(fakeResponse){
            is NetworkResult.Success -> {
                //then
                assertEquals(FakeRemoteRepositoryImpl.fakeActorTvResponse,fakeResponse.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }
}