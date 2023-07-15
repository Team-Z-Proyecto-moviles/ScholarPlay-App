package com.scholar.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class TeacherModel (
   @SerializedName("_id") val id: String,
   @SerializedName("name") val name: String
        )