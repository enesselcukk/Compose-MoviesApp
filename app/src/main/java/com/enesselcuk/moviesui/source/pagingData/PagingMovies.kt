package com.enesselcuk.moviesui.source.pagingData

import androidx.paging.*
import com.enesselcuk.moviesui.source.model.response.Result
import com.enesselcuk.moviesui.source.remote.MoviesService
import retrofit2.HttpException


class PagingMovies(
    private val remoteApi: MoviesService,
    private val categoryName: String?,
    private val language: String?
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        var position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val responseCategory = remoteApi.getMovies(title = categoryName, language = language, page = position)
            val repos = responseCategory.results
            val nextKey = if (repos.isEmpty()) null
                          else position + 1

            LoadResult.Page(
                data = responseCategory.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position.minus(1),
                nextKey = nextKey
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }


    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

}

