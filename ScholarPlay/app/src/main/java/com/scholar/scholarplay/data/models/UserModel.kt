package com.scholar.scholarplay.data.models

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("_id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("avatar") val avatar : String,
    @SerializedName("status") val status : String



        )