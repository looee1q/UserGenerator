package com.example.usergenerator.data.dto

data class UserDto(
    val gender: String,
    val name: NameDto,
    val location: LocationDto,
    val email: String,
    val login: LoginDto,
    val dob: UserDateOfBirthdayDto,
    val phone: String,
    val cell: String,
    val picture: UserPicture,
    val nat: String
)