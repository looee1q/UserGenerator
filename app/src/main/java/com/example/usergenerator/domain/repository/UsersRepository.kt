package com.example.usergenerator.domain.repository

import com.example.usergenerator.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getRandomUsers(): Flow<SearchResult>
}