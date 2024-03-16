package com.example.usergenerator.data.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.usergenerator.data.SearchResultData
import kotlinx.coroutines.flow.Flow

interface NetworkClient {

    val connectivityManager: ConnectivityManager

    fun getUsersFromNetwork(usersQuantity: Int): Flow<SearchResultData>

    fun isConnected(): Boolean {

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
}