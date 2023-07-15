package com.scholar.scholarplay.ui.avatar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.repository.CredentialsRepository
import kotlinx.coroutines.launch

class AvatarViewModel (private val repository: CredentialsRepository): ViewModel() {

    fun updateAvatar(user: String, avatar: String) {
        viewModelScope.launch {
            repository.updateAvatar(user, avatar)
        }
    }

    fun updateTeacherAvatar(teacher: String, avatar: String){
        viewModelScope.launch {
            repository.updateAvatarTeacher(teacher, avatar)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                AvatarViewModel(app.credentialsRepository)
            }
        }
    }
}