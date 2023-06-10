package com.example.scholarplay.repository

import android.util.Log
import androidx.startup.AppInitializer
import com.example.scholarplay.network.ApiResponse
import com.example.scholarplay.network.dto.login.LoginRequest
import com.example.scholarplay.network.dto.register.RegisterRequest
import com.example.scholarplay.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class CredentialsRepository(private val api: AuthService) {

    suspend fun login(email: String, password: String): ApiResponse<String>{
        try {
            val response = api.login(LoginRequest(email, password))
            return ApiResponse.Succes(response.token)
        } catch (e: HttpException) {
            if (e.code() == 400){
                return ApiResponse.ErrorWithMessege("Wrong password")
            }

            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

    suspend fun register(name: String, email: String, password: String, status: String) : ApiResponse<String>{
        try {
            val response = api.registerStudent(RegisterRequest(name, email, password, status))
            return ApiResponse.Succes(response.message)

        }catch (e: HttpException){
            if (e.code() == 404){
                return ApiResponse.ErrorWithMessege("Student doesn't exists")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

    suspend fun registerTeacher(name: String, email: String, password: String, status: String): ApiResponse<String>{
        try {
            val response = api.registerTeacher(RegisterRequest(name,email,password,status))
            return ApiResponse.Succes(response.message)
        }catch (e: HttpException){
            if (e.code() == 404){
                return ApiResponse.ErrorWithMessege("Teacher doesn't exist")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }

    suspend fun getStatus(token: String) : ApiResponse<String>{
        try {
            val response = api.getStatus(token)
            return ApiResponse.Succes(response.status)
        } catch (e: HttpException){
            if (e.code() == 404){
                return ApiResponse.ErrorWithMessege("User not found")
            }
            return ApiResponse.Error(e)
        }catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}