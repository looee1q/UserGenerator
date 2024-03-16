package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface GetUserDetailsByIdUseCase {

    operator fun invoke(userId: Int): Flow<DatabaseResult<UserDetails>>
}