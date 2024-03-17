package com.example.usergenerator.domain.repository

interface ExternalNavigationRepository {

    fun openContactsApp(phoneNumber: String)

    fun openEmailApp(email: String)

    fun openMapApp(latitude: String, longitude: String)
}