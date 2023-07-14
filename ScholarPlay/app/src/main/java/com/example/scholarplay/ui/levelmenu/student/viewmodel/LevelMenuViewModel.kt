package com.example.scholarplay.ui.levelmenu.student.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.data.models.LevelModel
import com.example.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LevelMenuViewModel(private val repository: ClassRoomRepository): ViewModel() {
    var classRoom = MutableLiveData<ClassModel>()


    fun getLevels(): Flow<PagingData<LevelModel>> {

        return repository
            .getLevelsPage(10, classRoom.value?.id!!)

        }





    fun setSelectedClass( selectedClass: ClassModel){
        classRoom.value = selectedClass

    }



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                LevelMenuViewModel(app.classRoomRepository)

            }
        }
    }
}