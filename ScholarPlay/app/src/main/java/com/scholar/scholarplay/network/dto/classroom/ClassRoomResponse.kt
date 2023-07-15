package com.scholar.scholarplay.network.dto.classroom

import com.scholar.scholarplay.data.models.ClassModel
import com.google.gson.annotations.SerializedName


data class ClassRoomResponse (
    @SerializedName("classrooms") val classroom : List<ClassModel>,
    @SerializedName("next") val next : String?,
    @SerializedName("previous") val previous: String?
        )