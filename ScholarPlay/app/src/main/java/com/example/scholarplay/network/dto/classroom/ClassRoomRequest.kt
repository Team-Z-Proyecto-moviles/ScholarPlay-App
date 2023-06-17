package com.example.scholarplay.network.dto.classroom

import com.google.gson.annotations.SerializedName

data class ClassRoomRequest (
    @SerializedName("name") val name : String,
    @SerializedName("teacher") val teacher : String,
    @SerializedName("image") val image : String,
    @SerializedName("section") val section : String
        )