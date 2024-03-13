package com.example.usergenerator.domain.models

data class UserBriefInfo(
    val firstName: String,
    val lastName: String,
    val streetNumber: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val smallPicture: String,
)
