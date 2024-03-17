package com.example.usergenerator.domain.usecase.implementation

import com.example.usergenerator.domain.repository.ExternalNavigationRepository
import com.example.usergenerator.domain.usecase.OpenContactsAppUseCase

class OpenContactsAppUseCaseImpl(
    private val externalNavigationRepository: ExternalNavigationRepository
) : OpenContactsAppUseCase {

    override fun invoke(phoneNumber: String) {
        externalNavigationRepository.openContactsApp(phoneNumber)
    }
}