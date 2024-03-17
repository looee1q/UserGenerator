package com.example.usergenerator.domain.usecase.implementation

import com.example.usergenerator.domain.repository.ExternalNavigationRepository
import com.example.usergenerator.domain.usecase.OpenMapAppUseCase

class OpenMapAppUseCaseImpl(
    private val externalNavigationRepository: ExternalNavigationRepository
) : OpenMapAppUseCase {

    override fun invoke(latitude: String, longitude: String) {
        externalNavigationRepository.openMapApp(latitude, longitude)
    }
}