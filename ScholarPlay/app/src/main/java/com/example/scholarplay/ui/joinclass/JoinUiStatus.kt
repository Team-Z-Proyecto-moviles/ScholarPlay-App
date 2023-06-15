package com.example.scholarplay.ui.joinclass

import java.lang.Exception

sealed class JoinUiStatus{

    object Resume: JoinUiStatus()

    class  Error(val exception: Exception): JoinUiStatus()

    data class ErrorWithMessage(val message: String): JoinUiStatus()

    object Success : JoinUiStatus()

}
