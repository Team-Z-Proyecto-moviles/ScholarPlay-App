package com.example.scholarplay.network

import com.example.scholarplay.network.dto.user.UserResponse
import java.lang.Exception

sealed class ApiUserResponse<T>{
    data class Success<T>(val data: UserResponse): ApiUserResponse<T>()

    data class Error<T>(val exception: Exception): ApiUserResponse<T>()

    data class ErrorWithMessege<T>(val messege: String): ApiUserResponse<T>()
}
