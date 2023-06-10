package com.example.scholarplay.network.retrofit

import com.example.scholarplay.network.service.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://scholarplay-api-production.up.railway.app/"

object RetrofitInstance {
    private var token = ""
    private var status = ""

    fun setToken(token: String){
        this.token = token
    }

    fun setStatus(status: String){
        this.status = status
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginService(): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}