package com.example.scholarplay.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scholarplay.data.models.LevelModel
import com.example.scholarplay.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class LevelPagingSource(private val service: AuthService, private val classRoom : String): PagingSource<Int,LevelModel>() {

    override fun getRefreshKey(state: PagingState<Int, LevelModel>): Int? {
        val pageSize = state.config.pageSize
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, LevelModel> {
            return try {
                val nextPage = params.key ?: 0
                val pageSize = params.loadSize
                val levelResponse = service.getLevels(classRoom, pageSize, nextPage)
                LoadResult.Page(
                    data = levelResponse.level,
                    nextKey = if (levelResponse.next != null) nextPage + pageSize else null,
                    prevKey = if (levelResponse.previous != null) nextPage- pageSize else null
                )
            } catch (e: IOException){
                LoadResult.Error(e)

        } catch (e: HttpException){
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}