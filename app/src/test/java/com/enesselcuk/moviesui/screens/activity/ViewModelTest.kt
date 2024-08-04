package com.enesselcuk.moviesui.screens.activity

import com.enesselcuk.moviesui.datastore.LocalDataStore
import com.enesselcuk.moviesui.domain.useCase.datastore.DataStoreUseCase
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @Mock private lateinit var activityViewModel: ActivityViewModel

    @Mock private lateinit var dataStoreUseCase: DataStoreUseCase

    @Mock private lateinit var localDataStore: LocalDataStore

    @Before
    fun setUp(){

    }
    @Test
    fun getTheme() {
    }
}