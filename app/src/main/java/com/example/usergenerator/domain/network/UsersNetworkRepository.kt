package com.example.usergenerator.domain.network

import com.example.usergenerator.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface UsersNetworkRepository {

    fun getRandomUsers(): Flow<SearchResult>
}