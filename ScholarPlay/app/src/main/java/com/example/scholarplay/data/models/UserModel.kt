package com.example.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("_id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("status") val status : String


        )