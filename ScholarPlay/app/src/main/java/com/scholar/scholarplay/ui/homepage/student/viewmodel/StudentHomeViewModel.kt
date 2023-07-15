package com.scholar.scholarplay.ui.homepage.student.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.data.models.ClassModel
import com.scholar.scholarplay.repository.ClassRoomRepository
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