package com.scholar.scholarplay.ui.levelmenu.student.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.data.models.ClassModel
import com.scholar.scholarplay.data.models.LevelModel
import com.scholar.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.flow.Flow

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