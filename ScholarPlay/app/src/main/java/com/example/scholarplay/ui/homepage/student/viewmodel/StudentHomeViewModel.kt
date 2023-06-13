package com.example.scholarplay.ui.homepage.student.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.network.dto.classroom.ClassRoomResponse
import com.example.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.launch

class StudentHomeViewModel(private val repository: ClassRoomRepository): ViewModel() {

    val classRooms = repository
        .getClassRoomPage(2)




    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                StudentHomeViewModel(app.classRoomRepository)
            }
        }
    }
}