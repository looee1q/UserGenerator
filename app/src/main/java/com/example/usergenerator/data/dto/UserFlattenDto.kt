package com.example.usergenerator.data.dto

data class UserFlattenDto(
    val gender: String,
    val title: String,
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
    val largePicture: String,
    val mediumPicture: String,
    val smallPicture: String,
    val nation: String
)