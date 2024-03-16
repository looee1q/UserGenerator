package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.UserDetails
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailsByIdUseCaseImpl(
    private val usersRepository: UsersRepository
) : GetUserDetailsByIdUseCase {

    override fun invoke(userId: Int): Flow<DatabaseResult<UserDetails>> {
        return usersRepository.getUserDetailsById(userId)
    }
}