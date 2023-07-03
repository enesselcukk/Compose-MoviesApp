package com.enesselcuk.moviesui.di

import com.enesselcuk.moviesui.repos.reposLocal.ReposLocal
import com.enesselcuk.moviesui.repos.reposLocal.RepositoryLocalImpl
import com.enesselcuk.moviesui.repos.reposRemote.Repos
import com.enesselcuk.moviesui.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.source.local.MoviesDatabase
import com.enesselcuk.moviesui.source.remote.MoviesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ReposModule {
    @Singleton
    private fun provideRepository(api: MoviesService) = RepositoryImpl(api)

    @Singleton
    private fun provideRepositoryLocal(database: MoviesDatabase) = RepositoryLocalImpl(database)

}

@Module
@InstallIn(ViewModelComponent::class)
interface ModuleRepos {
    @Binds
    fun reposModule(repos: RepositoryImpl): Repos

    @Binds
    fun reposLocalModule(repos: RepositoryLocalImpl): ReposLocal


}