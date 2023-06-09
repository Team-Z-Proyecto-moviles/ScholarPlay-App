package com.example.scholarplay.ui.register.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.network.ApiResponse
import com.example.scholarplay.repository.CredentialsRepository
import com.example.scholarplay.ui.register.RegisterUiStatus
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    var name = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")
    var confirmpassword = MutableLiveData("")
    var ocupation = MutableLiveData("")

    fun getOccupation(position: Int){
       if(position == 0){
           ocupation.value = "student"
       }

        if (position == 1){
            ocupation.value = "teacher"
        }
    }

    private val _status = MutableLiveData<RegisterUiStatus>(RegisterUiStatus.Resume)
    val status: LiveData<RegisterUiStatus>
        get() = _status

    private fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _status.postValue(
                when(val response = repository.register(name, email, password)){
                    is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessege -> RegisterUiStatus.ErrorWithMessage(response.messege)
                    is ApiResponse.Succes -> RegisterUiStatus.Success
                }
            )
        }
    }

    private fun registerTeacher(name: String, email: String, password: String){
        viewModelScope.launch {
            _status.postValue(
                when(val response = repository.registerTeacher(name, email, password)){
                    is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessege -> RegisterUiStatus.ErrorWithMessage(response.messege)
                    is ApiResponse.Succes -> RegisterUiStatus.Success
                }
            )
        }
    }

    fun onRegister() {
        if (!validateData()) {
            _status.value = RegisterUiStatus.ErrorWithMessage("Wrong information")
            return
        }

        if(ocupation.value == "student"){
            register(name.value!!, email.value!!, password.value!!)
        }

        if (ocupation.value == "teacher"){
            registerTeacher(name.value!!,email.value!!,password.value!!)
        }



        }


    private fun validateData(): Boolean {
        when {

            name.value.isNullOrEmpty() -> return false
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false
            password.value != confirmpassword.value -> return false


        }
        return true
    }

    fun clearStatus(){
        _status.value = RegisterUiStatus.Resume
    }

    fun clearData() {
        name.value = ""
        email.value = ""
        password.value = ""
        confirmpassword.value =""


    }



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }
}