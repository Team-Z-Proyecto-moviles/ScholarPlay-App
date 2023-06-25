package com.example.scholarplay.network.dto.level

import com.example.scholarplay.data.models.LevelModel
import com.google.gson.annotations.SerializedName

data class LevelResponse (
    @SerializedName("homeworks") val level : List<LevelModel>,
    @SerializedName("next") val next : String?,
    @SerializedName("previous") val previous: String?
        )