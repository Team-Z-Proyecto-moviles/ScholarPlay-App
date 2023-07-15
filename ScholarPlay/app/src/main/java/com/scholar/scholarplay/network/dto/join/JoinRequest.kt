package com.scholar.scholarplay.network.dto.join

import com.google.gson.annotations.SerializedName

data class JoinRequest (
    @SerializedName("studentId") val student : String,
    @SerializedName("codeClassroom") val classRoom: String
        )