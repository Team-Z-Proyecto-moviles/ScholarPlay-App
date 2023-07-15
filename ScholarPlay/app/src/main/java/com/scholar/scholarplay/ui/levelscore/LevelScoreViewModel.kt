package com.scholar.scholarplay.ui.levelscore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.data.models.LevelModel
import com.scholar.scholarplay.repository.ClassRoomRepository

class LevelScoreViewModel(private val repository: ClassRoomRepository) : ViewModel() {
    var level = MutableLiveData<LevelModel>()


    fun setSelectedClass( selectedLevel: LevelModel){
        level.value = selectedLevel
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                LevelScoreViewModel(app.classRoomRepository)

            }
        }
    }
}