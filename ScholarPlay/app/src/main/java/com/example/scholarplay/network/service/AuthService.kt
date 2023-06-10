package com.example.scholarplay.network.service

import com.example.scholarplay.network.dto.login.LoginRequest
import com.example.scholarplay.network.dto.login.LoginResponse
import com.example.scholarplay.network.dto.register.RegisterRequest
import com.example.scholarplay.network.dto.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/both/signin")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/student/register")
    suspend fun registerStudent(@Body credentials: RegisterRequest): RegisterResponse

    @POST("api/teacher/register")
    suspend fun registerTeacher(@Body credentials: RegisterRequest): RegisterResponse
}