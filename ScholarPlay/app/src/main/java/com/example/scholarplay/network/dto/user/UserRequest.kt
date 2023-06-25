package com.example.scholarplay.network.dto.user

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
        )