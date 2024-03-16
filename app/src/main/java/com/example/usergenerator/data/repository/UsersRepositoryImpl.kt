package com.example.usergenerator.data.repository

import com.example.usergenerator.data.SearchResultData
import com.example.usergenerator.data.database.AppDatabase
import com.example.usergenerator.data.mapper.Mapper
import com.example.usergenerator.data.network.NetworkClient
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val mapper: Mapper,
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase
) : UsersRepository {

    override fun getRandomUsers(): Flow<SearchResult> = flow {
        networkClient.getUsersFromNetwork(USERS_QUANTITY).collect {
            when (it) {
                is SearchResultData.Success -> {
                    appDatabase.getUsersDao().deleteAll()
                    appDatabase.getUsersDao().insertAll(it.data)

                    val usersFromDatabase = appDatabase.getUsersDao().getAllUsers()
                    val usersWithBriefInfo = usersFromDatabase.map {
                        mapper.fromUserEntityToUserBriefInfo(it)
                    }

                    emit(SearchResult.Success(usersWithBriefInfo))
                }
                SearchResultData.Error -> {
                    emit(SearchResult.Error)
                }
                SearchResultData.NoInternet -> {
                    emit(SearchResult.NoInternet)
                }
            }
        }
    }

    companion object {
        const val USERS_QUANTITY = 2
    }
}