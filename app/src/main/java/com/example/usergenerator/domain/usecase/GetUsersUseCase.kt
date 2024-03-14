package com.example.usergenerator.domain.usecase

import com.example.usergenerator.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    fun getRandomUsers(): Flow<SearchResult>
}