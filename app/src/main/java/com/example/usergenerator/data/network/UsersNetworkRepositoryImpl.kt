package com.example.usergenerator.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.usergenerator.data.convertor.Convertor
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.network.UsersNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersNetworkRepositoryImpl(
    private val context: Context
) : UsersNetworkRepository {

    override fun getRandomUsers(): Flow<SearchResult> = flow {
        Log.d("UsersNetworkRepositoryImpl", "Я дошел до репозитория")
        if (!isConnected()) {
            emit(SearchResult.NoInternet)
        } else {
            val response = RetrofitClient.api.getUsers(USERS_QUANTITY)
            Log.d("UsersNetworkRepositoryImpl", "response code is ${response.code()}")
            if (response.isSuccessful) {
                val usersWithBriefInfo = response.body()?.results?.map {
                    Convertor.fromUserDtoToUserBriefInfo(it)
                }.orEmpty()

                emit(SearchResult.Success(usersWithBriefInfo))
            } else {
                emit(SearchResult.Error)
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val capabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )

        capabilities?.let {
            return when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }

        return false
    }

    companion object {
        const val USERS_QUANTITY = 20
    }
}