package com.example.usergenerator.data.repository

import com.example.usergenerator.data.network.NetworkClient
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    private val networkClient: NetworkClient
) : UsersRepository {

    override fun getRandomUsers(): Flow<SearchResult> {
        return networkClient.getUsers(USERS_QUANTITY)
    }

    companion object {
        const val USERS_QUANTITY = 20
    }
}