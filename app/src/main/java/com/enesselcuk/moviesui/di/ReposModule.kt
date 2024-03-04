package com.enesselcuk.moviesui.di

import android.content.Context
import com.enesselcuk.moviesui.data.local.MoviesDatabase
import com.enesselcuk.moviesui.data.remote.MoviesService
import com.enesselcuk.moviesui.data.repos.reposLocal.RepositoryLocalImpl
import com.enesselcuk.moviesui.data.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.datastore.LocalDataStore
import com.enesselcuk.moviesui.datastore.PreferencesDataStoreImpl
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.util.NetworkConnectionInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object ReposModule {

    @Singleton
    fun provideRepository(api: MoviesService) = RepositoryImpl(api)

    @Singleton
     fun provideRepositoryLocal(database: MoviesDatabase) = RepositoryLocalImpl(database)

    @Provides
    @Singleton
     fun preferencesDataStore(@ApplicationContext context: Context) = PreferencesDataStoreImpl(context)

}

@Module
@InstallIn(SingletonComponent::class)
interface ModuleRepos {

    @Binds
    fun reposModule(repos: RepositoryImpl): Repos

    @Binds
    fun reposLocalModule(repos: RepositoryLocalImpl): ReposLocal

    @Binds
    fun localDataStore(preferences: PreferencesDataStoreImpl): LocalDataStore


}
