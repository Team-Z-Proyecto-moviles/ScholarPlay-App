package com.example.scholarplay.ui.profile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.UserModel
import com.example.scholarplay.network.dto.user.UserResponse
import com.example.scholarplay.repository.CredentialsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private  val repository: CredentialsRepository) : ViewModel() {

    var userData = MutableLiveData<UserResponse>()

    fun getUser(token: String){
        viewModelScope.launch {
          userData.value =  repository.getUserProfile(token)
        }

    }

    fun updateUser(user: String, name: String, email: String){
        viewModelScope.launch {
            repository.updateUser(user,name,email)
        }
    }



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                ProfileViewModel(app.credentialsRepository)

            }
        }
    }
}