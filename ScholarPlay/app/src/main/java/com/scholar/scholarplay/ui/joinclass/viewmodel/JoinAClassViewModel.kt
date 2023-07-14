package com.scholar.scholarplay.ui.joinclass.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.network.ApiResponse
import com.scholar.scholarplay.repository.CredentialsRepository
import com.scholar.scholarplay.ui.joinclass.JoinUiStatus
import kotlinx.coroutines.launch

class JoinAClassViewModel(private val repository: CredentialsRepository): ViewModel() {
    var student = MutableLiveData("")
    var code = MutableLiveData("")


    private val _status = MutableLiveData<JoinUiStatus>(JoinUiStatus.Resume)

    val status : MutableLiveData<JoinUiStatus>
        get() = _status


    fun joinAClass(student: String, code : String){
        viewModelScope.launch {
            _status.postValue(
                when(val response = repository.joinClassRoom(student, code)){
                    is ApiResponse.Error -> JoinUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessege -> JoinUiStatus.ErrorWithMessage(response.messege)
                    is ApiResponse.Succes -> JoinUiStatus.Success
                }
            )
        }
    }

    fun onJoining(){
        if(!validateData()){
            _status.value = JoinUiStatus.ErrorWithMessage("Wrong Code")
            return
        }
        joinAClass(student.value!!, code.value!!)

    }

    private fun validateData():Boolean{
        when{
            student.value.isNullOrEmpty() -> return false
            code.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearStatus(){
        _status.value = JoinUiStatus.Resume
    }

    fun clearData(){
        student.value = ""
        code.value = ""
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ScholarPlayApplication
                JoinAClassViewModel(app.credentialsRepository)
            }
        }
    }
}