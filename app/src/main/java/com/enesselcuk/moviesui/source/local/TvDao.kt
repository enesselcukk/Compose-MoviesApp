package com.enesselcuk.moviesui.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesselcuk.moviesui.source.model.response.DetailResponse
import com.enesselcuk.moviesui.source.model.response.MoviesResponse
import com.enesselcuk.moviesui.source.model.response.TvDetailResponse

@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(TvEntity: TvDetailResponse)

    @Query("DELETE FROM TvDetailEntity WHERE id= :idTv")
    suspend fun deleteID(idTv: Int): Int

    @Query("SELECT * FROM TvDetailEntity")
    suspend fun allTv(): List<TvDetailResponse>

    @Query("SELECT * FROM TvDetailEntity WHERE id =:id")
    suspend fun getIdFavorite(id: Int): TvDetailResponse
}