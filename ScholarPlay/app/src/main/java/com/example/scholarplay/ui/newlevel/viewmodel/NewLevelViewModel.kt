package com.example.scholarplay.ui.newlevel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.launch

class NewLevelViewModel(private val repository: ClassRoomRepository): ViewModel() {
    var name = MutableLiveData("")
    var classRoom = MutableLiveData("")


    private fun newLevel(name: String, classRoom: String){
        viewModelScope.launch {
            repository.createLevel(name, classRoom)
        }
    }


    fun onNewLevel(){
        newLevel(name.value!!, classRoom.value!!)
    }

    fun getClassRoom(newId: String){
        classRoom.value = newId
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                NewLevelViewModel(app.classRoomRepository)
            }
        }
    }
}