package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.UserBriefInfo
import kotlinx.coroutines.flow.Flow

interface GetUsersFromDatabaseUseCase {

    operator fun invoke(): Flow<DatabaseResult<List<UserBriefInfo>>>
}