package com.scholar.scholarplay.ui.login

import com.scholar.scholarplay.network.dto.user.UserResponse
import java.lang.Exception

sealed class LoginUiStatus {
    object Resume : LoginUiStatus()
    class Error(val exception: Exception) : LoginUiStatus()
    data class ErrorWithMessage(val message: String) : LoginUiStatus()
    data class Success(val token: String) : LoginUiStatus()

    data class  Succes2(val user_data: UserResponse) : LoginUiStatus()
}