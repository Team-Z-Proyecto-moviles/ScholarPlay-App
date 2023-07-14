package com.scholar.scholarplay.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scholar.scholarplay.data.models.ClassModel
import com.scholar.scholarplay.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class ClassRoomPagingSource(private val service: AuthService, private val user: String):PagingSource<Int,ClassModel>() {

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
            val  classRoomResponse= service.getClassRoom(user,pageSize, nextPage)
            LoadResult.Page(
                data = classRoomResponse.classroom,
                nextKey = if (classRoomResponse.next != null) nextPage + pageSize else null,
                prevKey = if (classRoomResponse.previous != null) nextPage - pageSize else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}