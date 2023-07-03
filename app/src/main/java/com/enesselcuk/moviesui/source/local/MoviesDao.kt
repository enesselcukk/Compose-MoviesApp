package com.enesselcuk.moviesui.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.source.model.response.MoviesResponse

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: DetailResponse)

    @Query("DELETE FROM detail_entity WHERE id= :idMovies")
    suspend fun deleteID(idMovies: Int): Int

    @Query("SELECT * FROM detail_entity")
    suspend fun allMovies(): List<DetailResponse>

    @Query("SELECT * FROM detail_entity WHERE id =:id")
    suspend fun getIdFavorite(id: Int): DetailResponse
}