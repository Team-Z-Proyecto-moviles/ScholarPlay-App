package com.scholar.scholarplay.network.service

import com.scholar.scholarplay.network.dto.avatar.AvatarRequest
import com.scholar.scholarplay.network.dto.classroom.ClassRoomRequest
import com.scholar.scholarplay.network.dto.classroom.ClassRoomResponse
import com.scholar.scholarplay.network.dto.join.JoinRequest
import com.scholar.scholarplay.network.dto.join.JoinResponse
import com.scholar.scholarplay.network.dto.level.LevelRequest
import com.scholar.scholarplay.network.dto.level.LevelResponse
import com.scholar.scholarplay.network.dto.login.LoginRequest
import com.scholar.scholarplay.network.dto.login.LoginResponse
import com.scholar.scholarplay.network.dto.register.RegisterRequest
import com.scholar.scholarplay.network.dto.register.RegisterResponse
import com.scholar.scholarplay.network.dto.user.UserRequest
import com.scholar.scholarplay.network.dto.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("api/homework/found/{classroom}")
    suspend fun getLevels(
        @Path("classroom") classRoom: String, @Query("limit") limit: Int, @Query("offset") offset: Int) : LevelResponse

    @POST("api/classroom/add-student/code/classroom")
    suspend fun joinClassRoom(@Body credentials: JoinRequest): JoinResponse

    @POST("api/classroom")
    suspend fun createClassRoom(@Body credentials: ClassRoomRequest)

    @POST("api/homework")
    suspend fun createLevel(@Body credentials: LevelRequest)

    @PUT("api/student/identifier/updateImage/{user}")
    suspend fun updateAvatar(@Path("user") user: String, @Body credentials: AvatarRequest)

    @PUT("api/teacher/identifier/updateImage/{teacher}")
    suspend fun updateAvatarTeacher(@Path("teacher") teacher: String, @Body credentials: AvatarRequest)

    @PUT("api/auth/update/data/user/{user}")
    suspend fun updateUser(@Path("user") user: String, @Body credentials: UserRequest)

}