package com.example.usergenerator.domain.usecase

interface OpenMapAppUseCase {

    operator fun invoke(latitude: String, longitude: String)
}