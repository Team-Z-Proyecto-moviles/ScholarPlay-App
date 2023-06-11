package com.example.scholarplay.network

import com.example.scholarplay.network.dto.user.UserResponse
import java.lang.Exception

sealed class ApiResponse<T>{
    data class Succes<T>(val data: T): ApiResponse<T>()

    data class Error<T>(val exception: Exception): ApiResponse<T>()

    data class ErrorWithMessege<T>(val messege: String): ApiResponse<T>()
}
