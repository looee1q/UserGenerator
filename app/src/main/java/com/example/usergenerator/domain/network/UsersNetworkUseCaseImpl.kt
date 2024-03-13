package com.example.usergenerator.domain.network

import com.example.usergenerator.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

class UsersNetworkUseCaseImpl(
    private val usersNetworkRepository: UsersNetworkRepository
) : UsersNetworkUseCase {

    override fun getRandomUsers(): Flow<SearchResult> {
        return usersNetworkRepository.getRandomUsers()
    }
}