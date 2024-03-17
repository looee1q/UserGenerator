package com.example.usergenerator.domain.usecase

interface OpenContactsAppUseCase {

    operator fun invoke(phoneNumber: String)
}