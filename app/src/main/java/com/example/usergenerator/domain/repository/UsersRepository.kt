package com.example.usergenerator.domain.repository

import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsersFromNetwork(): Flow<SearchResult>

    fun getUsersFromDatabase(): Flow<DatabaseResult<List<UserBriefInfo>>>

    fun getUserDetailsById(userId: Int): Flow<UserDetails>
}