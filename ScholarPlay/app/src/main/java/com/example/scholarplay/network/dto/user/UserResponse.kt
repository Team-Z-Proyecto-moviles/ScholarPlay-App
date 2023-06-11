package com.example.scholarplay.network.dto.user

import com.example.scholarplay.data.models.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse (
        @SerializedName("user") val user: UserModel

        )