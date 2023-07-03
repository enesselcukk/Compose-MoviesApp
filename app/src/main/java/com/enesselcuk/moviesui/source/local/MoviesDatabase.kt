package com.enesselcuk.moviesui.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enesselcuk.moviesui.source.model.response.*

@Database(
    entities = [
        MoviesResponse::class,
        DetailResponse::class,
        TvDetailResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvDao(): TvDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java, "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}