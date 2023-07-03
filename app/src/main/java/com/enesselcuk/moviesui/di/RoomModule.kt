package com.enesselcuk.moviesui.di

import android.content.Context
import androidx.room.Room
import com.enesselcuk.moviesui.source.local.MoviesDao
import com.enesselcuk.moviesui.source.local.MoviesDatabase
import com.enesselcuk.moviesui.source.local.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(
        db: MoviesDatabase,
    ): MoviesDao = db.moviesDao()

    @Provides
    @Singleton
    fun provideTvDao(
        db: MoviesDatabase,
    ): TvDao = db.tvDao()
}