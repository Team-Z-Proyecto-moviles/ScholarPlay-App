package com.example.scholarplay.repository

import com.example.scholarplay.network.service.AuthService

class ClassRoomRepository(private val api: AuthService) {

    suspend fun getClassRooms(user: String) = api.getClassRoom(user)
}