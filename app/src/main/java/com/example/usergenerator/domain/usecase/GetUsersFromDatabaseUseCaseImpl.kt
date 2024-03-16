package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class GetUsersFromDatabaseUseCaseImpl(
    private val usersRepository: UsersRepository
) : GetUsersFromDatabaseUseCase {

    override fun invoke(): Flow<DatabaseResult<List<UserBriefInfo>>> {
        return usersRepository.getUsersFromDatabase()
    }
}