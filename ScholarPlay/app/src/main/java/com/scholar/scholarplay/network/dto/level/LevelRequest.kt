package com.scholar.scholarplay.network.dto.level

import com.google.gson.annotations.SerializedName

data class LevelRequest(
    @SerializedName("name") val name: String,
    @SerializedName("classroom") val classRoom: String
)
