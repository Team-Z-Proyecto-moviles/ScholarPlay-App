package com.example.scholarplay.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.network.ClassRoomPagingSource
import com.example.scholarplay.network.service.AuthService

class ClassRoomRepository(private val api: AuthService) {


    suspend fun getClassRooms(user: String, limit: Int, offset: Int) =
        api.getClassRoom(user, limit, offset)

    fun getClassRoomPage(pageSize: Int, user: String) = Pager(
        PagingConfig(pageSize)
    ){
        ClassRoomPagingSource(api, user)
    }.flow
}