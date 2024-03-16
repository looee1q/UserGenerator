package com.example.usergenerator.data.network

import android.net.ConnectivityManager
import android.util.Log
import com.example.usergenerator.data.SearchResultData
import com.example.usergenerator.data.mapper.Mapper
import com.example.usergenerator.data.repository.UsersRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    override val connectivityManager: ConnectivityManager,
    private val mapper: Mapper
) : NetworkClient {

    private val converterFactory = GsonConverterFactory.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_RANDOM_USER_URL)
        .addConverterFactory(converterFactory)
        .build()

    private val api: RandomUserApi by lazy {
        retrofit.create(RandomUserApi::class.java)
    }

    override fun getUsersFromNetwork(usersQuantity: Int): Flow<SearchResultData>  = flow {
        if (!isConnected()) {
            emit(SearchResultData.NoInternet)
        } else {
            val response = api.getUsers(usersQuantity)
            if (response.isSuccessful) {
                val users = response.body()?.results?.map {
                    mapper.fromUserDtoToUserEntity(it)
                }.orEmpty()
                Log.d("RetrofitClient", "Users1 ${users.map { it.firstName + it.lastName }}")
                emit(SearchResultData.Success(users))
            } else {
                emit(SearchResultData.Error)
            }
        }
    }

    companion object {
        private const val BASE_RANDOM_USER_URL: String = "https://randomuser.me/api/"
    }
}