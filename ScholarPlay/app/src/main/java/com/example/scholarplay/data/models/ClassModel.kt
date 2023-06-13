package com.example.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class ClassModel (
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("teacher") val teacher: TeacherModel
        )