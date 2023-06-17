package com.example.scholarplay.ui.createclass.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.repository.ClassRoomRepository
import kotlinx.coroutines.launch

class CreateClassViewModel(private val repository: ClassRoomRepository): ViewModel() {
    var name = MutableLiveData("")
    var teacher = MutableLiveData("")
    var image = MutableLiveData("")


     private fun createClass(name: String, teacher: String, image: String, section: String){
        viewModelScope.launch {
            repository.createClassRoom(name, teacher, image, section)
        }
    }

    fun getImage(newImage: String){
        image.value = newImage
    }

    fun onCreateClass(name: String, teacher: String, section: String){
        createClass(name, teacher, image.value!!, section)
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                CreateClassViewModel(app.classRoomRepository)
            }
        }
    }

}