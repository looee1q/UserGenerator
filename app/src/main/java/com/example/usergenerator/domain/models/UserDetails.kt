package com.example.usergenerator.domain.models

data class UserDetails(
    val id: Int,
    val gender: String,
    val firstName: String,
    val lastName: String,
    val streetNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val locationLatitude: String,
    val locationLongitude: String,
    val email: String,
    val username: String,
    val dateOfBirthday: String,
    val age: String,
    val phone: String,
    val cellPhone: String,
    val picture: String,
    val nation: String
)