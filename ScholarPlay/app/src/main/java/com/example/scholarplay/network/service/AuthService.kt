package com.example.scholarplay.network.service

import com.example.scholarplay.network.dto.classroom.ClassRoomResponse
import com.example.scholarplay.network.dto.login.LoginRequest
import com.example.scholarplay.network.dto.login.LoginResponse
import com.example.scholarplay.network.dto.register.RegisterRequest
import com.example.scholarplay.network.dto.register.RegisterResponse
import com.example.scholarplay.network.dto.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {

    @POST("api/auth/both/signin")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/student/register")
    suspend fun registerStudent(@Body credentials: RegisterRequest): RegisterResponse

    @POST("api/teacher/register")
    suspend fun registerTeacher(@Body credentials: RegisterRequest): RegisterResponse

    @GET("api/auth/find/alldata/user/{token}")
    suspend fun getUser(@Path("token") token: String):UserResponse

    @GET("api/classroom/students/classrooms/plusname/teacher/{user}")
    suspend fun getClassRoom(
        @Path("user") user: String, @Query("limit") limit: Int, @Query("offset") offset: Int): ClassRoomResponse
}