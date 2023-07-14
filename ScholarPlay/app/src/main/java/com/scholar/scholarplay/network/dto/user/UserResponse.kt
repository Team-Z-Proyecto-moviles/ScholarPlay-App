package com.scholar.scholarplay.network.dto.user

import com.scholar.scholarplay.data.models.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse (
        @SerializedName("user") val user: UserModel

        )