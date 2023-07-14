package com.example.scholarplay.ui.levelscore

import android.text.Spannable.Factory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.LevelModel
import com.example.scholarplay.repository.ClassRoomRepository

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