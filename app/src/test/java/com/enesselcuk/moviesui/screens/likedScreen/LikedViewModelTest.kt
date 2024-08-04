package com.enesselcuk.moviesui.screens.likedScreen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.enesselcuk.moviesui.data.local.MoviesDao
import com.enesselcuk.moviesui.data.local.MoviesDatabase
import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl
import com.enesselcuk.moviesui.data.repository.FakeLocalRepositoryImpl.FakeLocalMoviesList.fakeDetailResponseList
import com.enesselcuk.moviesui.util.NetworkResult
import io.mockk.coEvery
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class LikedViewModelTest {

    @Mock
    private lateinit var database: MoviesDatabase

    @Mock
    private lateinit var moviesDao: MoviesDao

    @Mock
    lateinit var fakeLocalRepositoryImpl: FakeLocalRepositoryImpl

    @Mock
    lateinit var likedViewModel: LikedViewModel

    @Mock
    lateinit var savedStateHandle: SavedStateHandle



    @Before
    fun setUpDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MoviesDatabase::class.java
        ).allowMainThreadQueries().build()

        moviesDao = database.moviesDao()

        fakeLocalRepositoryImpl = FakeLocalRepositoryImpl()

        savedStateHandle = SavedStateHandle()

        likedViewModel = LikedViewModel(fakeLocalRepositoryImpl, savedStateHandle)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun getChooseList() = runTest {

    }

    @Test
    fun getChooseListTv() {
    }

    @Test
    fun getGetFlowFavorite() {
    }

    @Test
    fun setRemove() {
    }

    @Test
    fun updateDb() {
    }

    @Test
    fun getFavorite() {
        // when
        coEvery { fakeLocalRepositoryImpl.getLiked() } returns flow {
            emit(NetworkResult.Success(fakeDetailResponseList))
        }

        likedViewModel.getFlowFavorite.value
    }

    @Test
    fun delete() {
    }

    @Test
    fun getGetFlowTvFavorite() {
    }

    @Test
    fun getTvFavorite() {
    }

    @Test
    fun deleteTv() {
    }
}