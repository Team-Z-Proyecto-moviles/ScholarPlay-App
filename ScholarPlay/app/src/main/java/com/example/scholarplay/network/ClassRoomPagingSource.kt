package com.example.scholarplay.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class ClassRoomPagingSource(private val service: AuthService):PagingSource<Int,ClassModel>() {

    override fun getRefreshKey(state: PagingState<Int, ClassModel>): Int? {
        val pageSize = state.config.pageSize
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, ClassModel> {
        return try {
            val nextPage = params.key ?: 0
            val pageSize = params.loadSize
            val  classRoomResponse= service.getClassRoom("64863da015ef55bf1203b989",pageSize, nextPage)
            LoadResult.Page(
                data = classRoomResponse.classroom,prevKey = null, nextKey = null)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}