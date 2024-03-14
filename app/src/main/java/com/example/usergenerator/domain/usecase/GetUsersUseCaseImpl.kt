package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class GetUsersUseCaseImpl(
    private val usersRepository: UsersRepository
) : GetUsersUseCase {

    override fun getRandomUsers(): Flow<SearchResult> {
        return usersRepository.getRandomUsers()
    }
}