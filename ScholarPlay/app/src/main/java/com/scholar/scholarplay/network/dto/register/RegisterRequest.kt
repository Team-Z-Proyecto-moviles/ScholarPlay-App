package com.scholar.scholarplay.network.dto.register

data class RegisterRequest (
    val name: String,
    val email: String,
    val password: String,
    val status: String

        )