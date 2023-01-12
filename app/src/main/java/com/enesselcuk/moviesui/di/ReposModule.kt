package com.enesselcuk.moviesui.di

import com.enesselcuk.moviesui.repos.Repos
import com.enesselcuk.moviesui.repos.RepositoryImpl
import com.enesselcuk.moviesui.source.remote.MoviesService
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
}

@Module
@InstallIn(ViewModelComponent::class)
interface ModuleRepos {
    @Binds
    fun reposModule(repos: RepositoryImpl): Repos
}