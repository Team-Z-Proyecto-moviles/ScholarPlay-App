package com.example.scholarplay.ui.login.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.network.ApiResponse
import com.example.scholarplay.repository.CredentialsRepository
import com.example.scholarplay.ui.login.LoginUiStatus
import kotlinx.coroutines.launch

class LoginViewmodel (private val repository: CredentialsRepository): ViewModel() {
    var email = MutableLiveData("")
    var password = MutableLiveData("")


    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: MutableLiveData<LoginUiStatus>
        get() = _status

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _status.postValue(
                when(val reponse = repository.login(email, password)){
                    is ApiResponse.Error -> LoginUiStatus.Error(reponse.exception)
                    is ApiResponse.ErrorWithMessege -> LoginUiStatus.ErrorWithMessage(reponse.messege)
                    is ApiResponse.Succes -> LoginUiStatus.Success(reponse.data)
                }
            )
        }
    }

    fun onLogin() {
        if (!valiateData()){
            _status.value = LoginUiStatus.ErrorWithMessage("Wrong information")
            return
        }
        login(email.value!!, password.value!!)
    }

    private fun valiateData(): Boolean{
        when {
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false

        }

        return true
    }

    fun clearData() {
        email.value = ""
        password.value = ""
    }

    fun clearStatus() {
        _status.value = LoginUiStatus.Resume
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                LoginViewmodel(app.credentialsRepository)
            }
        }
    }


}