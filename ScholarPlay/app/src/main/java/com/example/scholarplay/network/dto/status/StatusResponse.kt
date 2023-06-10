package com.example.scholarplay.network.dto.status

import com.google.gson.annotations.SerializedName

data class StatusResponse (
        @SerializedName("status") val status: String

        )