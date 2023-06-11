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
        setStatus(getStatus())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    fun getStatus(): String = prefs.getString(USER_STATUS,"")!!

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getAPIService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveId(status: String) {
        val editor = prefs.edit()
        editor.putString(USER_STATUS, status)
        editor.apply()
    }



    companion object{
        const val USER_TOKEN = "user_token"
        const val USER_STATUS = "user_status"
    }
}