package dev.kaman.core.domain.model.auth

data class UserRegister(
    val email: String,
    val password: String,
    val name: String
//    val year: Int
)
