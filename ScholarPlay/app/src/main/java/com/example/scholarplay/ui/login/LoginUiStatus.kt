package com.example.scholarplay.ui.login

import java.lang.Exception

sealed class LoginUiStatus {
    object Resume : LoginUiStatus()
    class Error(val exception: Exception) : LoginUiStatus()
    data class ErrorWithMessage(val message: String) : LoginUiStatus()
    data class Success(val token: String) : LoginUiStatus()

    data class  Succes2(val status: String) : LoginUiStatus()
}