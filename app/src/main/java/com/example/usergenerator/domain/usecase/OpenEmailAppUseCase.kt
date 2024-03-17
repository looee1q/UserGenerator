package com.example.usergenerator.domain.usecase

interface OpenEmailAppUseCase {

    operator fun invoke(email: String)
}