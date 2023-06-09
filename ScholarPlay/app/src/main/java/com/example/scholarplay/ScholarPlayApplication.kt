package com.example.scholarplay

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.scholarplay.network.retrofit.RetrofitInstance
import com.example.scholarplay.repository.CredentialsRepository

class ScholarPlayApplication : Application() {
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("ScholarPlay", Context.MODE_PRIVATE)
    }

    private fun getAPIService() = with(RetrofitInstance) {
        setToken(getToken())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getAPIService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }



    companion object{
        const val USER_TOKEN = "user_token"
    }
}