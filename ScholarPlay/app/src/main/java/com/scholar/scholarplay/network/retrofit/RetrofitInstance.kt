package com.scholar.scholarplay.network.retrofit

import com.scholar.scholarplay.network.service.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dufferx.me/"


object RetrofitInstance {
    private var token = ""
    private var id = ""
    private var role = ""

    fun setToken(token: String){
        this.token = token
    }

    fun setId(status: String){
        this.id = status
    }

    fun setRole(role: String){
        this.role = role
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginService(): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    fun getApiUrl(): String{
        return BASE_URL

    }

}