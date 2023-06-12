package com.example.scholarplay.network.dto.classroom

import com.example.scholarplay.data.models.ClassModel
import com.google.gson.annotations.SerializedName


data class ClassRoomResponse (
    @SerializedName("classrooms") val classroom : List<ClassModel>
        )