package com.example.usergenerator.data.dto

data class LocationDto(
    val street: StreetDto,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: LocationCoordinatesDto
)