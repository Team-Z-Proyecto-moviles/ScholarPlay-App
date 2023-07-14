package com.scholar.scholarplay

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.scholar.scholarplay.network.retrofit.RetrofitInstance
import com.scholar.scholarplay.repository.ClassRoomRepository
import com.scholar.scholarplay.repository.CredentialsRepository

class ScholarPlayApplication : Application() {
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("ScholarPlay", Context.MODE_PRIVATE)
    }

    private fun getAPIService() = with(RetrofitInstance) {
        setToken(getToken())
        setId(getId())
        setRole(getRole())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    fun getId(): String = prefs.getString(USER_ID,"")!!

    fun getRole(): String = prefs.getString(USER_ROLE, "")!!

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getAPIService())
    }

    val classRoomRepository: ClassRoomRepository by lazy {
        ClassRoomRepository(getAPIService())
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveId(status: String) {
        val editor = prefs.edit()
        editor.putString(USER_ID, status)
        editor.apply()
    }

    fun saveRole(role : String){
        val editor = prefs.edit()
        editor.putString(USER_ROLE, role)
        editor.apply()
    }



    companion object{
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_status"
        const val USER_ROLE = "user_role"
    }
}