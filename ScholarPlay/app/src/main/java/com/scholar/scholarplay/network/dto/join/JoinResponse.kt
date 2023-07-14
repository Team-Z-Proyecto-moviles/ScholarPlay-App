package com.scholar.scholarplay.network.dto.join

import com.google.gson.annotations.SerializedName

data class JoinResponse (
    @SerializedName("message") val message: String
        )