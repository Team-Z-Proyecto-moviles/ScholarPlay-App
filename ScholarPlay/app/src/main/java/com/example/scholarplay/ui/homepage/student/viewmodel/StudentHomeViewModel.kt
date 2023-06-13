package com.example.scholarplay.ui.homepage.student.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.flow.Flow

class StudentHomeViewModel(private val repository: ClassRoomRepository): ViewModel() {


    fun getClassRoom(user: String): Flow<PagingData<ClassModel>> {

        return repository
            .getClassRoomPage(2, user)

    }




    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                StudentHomeViewModel(app.classRoomRepository)
            }
        }
    }
}