package com.example.scholarplay.network.dto.avatar

import com.google.gson.annotations.SerializedName

data class AvatarRequest(
    @SerializedName("avatar") val avatar : String
)
