package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface GetUsersFromNetworkUseCase {

    operator fun invoke(): Flow<SearchResult>
}