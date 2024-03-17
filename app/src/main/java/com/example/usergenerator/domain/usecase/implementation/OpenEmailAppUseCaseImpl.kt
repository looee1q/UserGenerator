package com.example.usergenerator.domain.usecase.implementation

import com.example.usergenerator.domain.repository.ExternalNavigationRepository
import com.example.usergenerator.domain.usecase.OpenEmailAppUseCase

class OpenEmailAppUseCaseImpl(
    private val externalNavigationRepository: ExternalNavigationRepository
) : OpenEmailAppUseCase {

    override fun invoke(email: String) {
        externalNavigationRepository.openEmailApp(email)
    }
}