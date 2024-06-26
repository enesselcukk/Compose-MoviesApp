package com.enesselcuk.moviesui.domain.useCase.detail

import com.enesselcuk.moviesui.data.model.response.DetailResponse
import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailLikedUseCaseTest {

    @Mock
    private lateinit var fakeLocalRepositoryImpl: FakeLocalRepositoryImpl
    @Mock
    private lateinit var detailLikedUseCase: DetailLikedUseCase


    @Before
    fun setUp() {
        fakeLocalRepositoryImpl = FakeLocalRepositoryImpl()
        detailLikedUseCase = DetailLikedUseCase(fakeLocalRepositoryImpl)
    }

    @Test
    operator fun invoke() = runTest {
        val fakeResponse = detailLikedUseCase.invoke(DetailResponse()).first()

        // when
        when(fakeResponse){
            is NetworkResult.Success -> {
                //then
                assertEquals(1, fakeResponse.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }
}