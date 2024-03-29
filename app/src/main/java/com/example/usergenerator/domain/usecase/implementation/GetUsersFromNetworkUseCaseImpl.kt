package com.example.usergenerator.domain.usecase.implementation

import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.repository.UsersRepository
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCase
import kotlinx.coroutines.flow.Flow

class GetUsersFromNetworkUseCaseImpl(
    private val usersRepository: UsersRepository
) : GetUsersFromNetworkUseCase {

    override operator fun invoke(): Flow<SearchResult> {
        return usersRepository.getUsersFromNetwork()
    }
}