package com.example.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class LevelModel (
    @SerializedName("_id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("classroom") val classRoomId : String
        )