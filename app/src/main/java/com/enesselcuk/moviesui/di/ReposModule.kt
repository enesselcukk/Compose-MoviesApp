package com.enesselcuk.moviesui.di

import com.enesselcuk.moviesui.domain.repository.ReposLocal
import com.enesselcuk.moviesui.data.repos.reposLocal.RepositoryLocalImpl
import com.enesselcuk.moviesui.domain.repository.Repos
import com.enesselcuk.moviesui.data.repos.reposRemote.RepositoryImpl
import com.enesselcuk.moviesui.data.local.MoviesDatabase
import com.enesselcuk.moviesui.data.remote.MoviesService
import dagger.Binds
import dagger.Module
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