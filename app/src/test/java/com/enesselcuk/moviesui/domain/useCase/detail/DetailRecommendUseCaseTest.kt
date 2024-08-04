package com.enesselcuk.moviesui.domain.useCase.detail

import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl
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
class DetailRecommendUseCaseTest {

    @Mock private lateinit var detailRecommendUseCase: DetailRecommendUseCase
    @Mock private lateinit var fakeRemoteRepositoryImpl: FakeRemoteRepositoryImpl


    @Before
    fun setUp(){
        fakeRemoteRepositoryImpl = FakeRemoteRepositoryImpl()
        detailRecommendUseCase = DetailRecommendUseCase(fakeRemoteRepositoryImpl)
    }
    @Test
    operator fun invoke() = runTest{
        val fakeResponse = detailRecommendUseCase.invoke(1,1,"tr").first()

        when(fakeResponse){
            is NetworkResult.Success -> {
                //then
                assertEquals(FakeRemoteRepositoryImpl.fakeMoviesResponse,fakeResponse.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }
}