package com.enesselcuk.moviesui.domain.useCase.detail

import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl
import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl.FakeLocalMoviesList.fakeDetailResponseList
import com.enesselcuk.moviesui.data.repository.FakeRemoteRepositoryImpl
import com.enesselcuk.moviesui.util.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailFavoriteUseCaseTest{

    @Mock private lateinit var detailFavoriteUseCase:DetailFavoriteUseCase

    @Mock private lateinit var fakeLocalRepositoryImpl: FakeLocalRepositoryImpl

    @Before
    fun setUp() {
        fakeLocalRepositoryImpl = FakeLocalRepositoryImpl()
        detailFavoriteUseCase = DetailFavoriteUseCase(fakeLocalRepositoryImpl)
    }

    @Test
    fun invoke()= runTest {
        val response = detailFavoriteUseCase.invoke().first()

        // when
        when(response){
            is NetworkResult.Success -> {
                //then
                Assert.assertEquals(fakeDetailResponseList, response.data)
            }
            is NetworkResult.Error -> {}
            is NetworkResult.Loading -> {}
        }
    }

}