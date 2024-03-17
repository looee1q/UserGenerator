package com.example.usergenerator.data.repository

import com.example.usergenerator.data.network.SearchResultData
import com.example.usergenerator.data.database.AppDatabase
import com.example.usergenerator.data.mapper.Mapper
import com.example.usergenerator.data.network.NetworkClient
import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.models.UserDetails
import com.example.usergenerator.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val mapper: Mapper,
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase
) : UsersRepository {

    override fun getUsersFromNetwork(): Flow<SearchResult> = flow {
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
                    emit(SearchResult.ErrorNetwork)
                }
                SearchResultData.NoInternet -> {
                    emit(SearchResult.NoInternet)
                }
            }
        }
    }

    override fun getUsersFromDatabase(): Flow<DatabaseResult<List<UserBriefInfo>>> = flow {
        val usersFromDatabase = appDatabase.getUsersDao().getAllUsers()
        val usersWithBriefInfo = usersFromDatabase.map {
            mapper.fromUserEntityToUserBriefInfo(it)
        }
        emit(DatabaseResult.Success(usersWithBriefInfo))
    }

    override fun getUserDetailsById(userId: Int): Flow<DatabaseResult<UserDetails>> = flow {
        val userEntity = appDatabase.getUsersDao().getUserById(userId)
        val userDetails = mapper.fromUserEntityToUserDetails(userEntity)
        emit(DatabaseResult.Success(userDetails))
    }

    companion object {
        const val USERS_QUANTITY = 20
    }
}