package com.example.scholarplay.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.network.ClassRoomPagingSource
import com.example.scholarplay.network.LevelPagingSource
import com.example.scholarplay.network.dto.classroom.ClassRoomRequest
import com.example.scholarplay.network.dto.level.LevelRequest
import com.example.scholarplay.network.service.AuthService

class ClassRoomRepository(private val api: AuthService) {


    suspend fun getClassRooms(user: String, limit: Int, offset: Int) =
        api.getClassRoom(user, limit, offset)

    suspend fun getLevels(classRoom: String, limit: Int, offset: Int) =
        api.getLevels(classRoom, limit, offset)

    suspend fun createClassRoom(name: String, teacher: String, image : String, section: String) =
        api.createClassRoom(ClassRoomRequest(name, teacher, image, section))

    suspend fun  createLevel(name: String, classRoom: String) =
        api.createLevel(LevelRequest(name, classRoom))

    fun getClassRoomPage(pageSize: Int, user: String) = Pager(
        PagingConfig(pageSize)
    ){
        ClassRoomPagingSource(api, user)
    }.flow

    fun getLevelsPage(pageSize: Int, classRoom: String) = Pager(
        PagingConfig(pageSize)
    ){
        LevelPagingSource(api, classRoom)
    }.flow
}