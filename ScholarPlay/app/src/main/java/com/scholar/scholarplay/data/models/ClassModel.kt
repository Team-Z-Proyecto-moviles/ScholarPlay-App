package com.scholar.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class ClassModel (
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("teacher") val teacher: TeacherModel,
    @SerializedName("image") val image: String,
    @SerializedName("section") val section: String
        )