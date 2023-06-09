package com.example.scholarplay.network.service

import com.example.scholarplay.network.dto.login.LoginRequest
import com.example.scholarplay.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse
}