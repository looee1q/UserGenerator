package com.example.usergenerator.data.network

import android.net.ConnectivityManager
import com.example.usergenerator.data.mapper.Mapper
import com.example.usergenerator.data.repository.UsersRepositoryImpl
import com.example.usergenerator.domain.models.SearchResult
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

    override fun getUsers(usersQuantity: Int): Flow<SearchResult>  = flow {
        if (!isConnected()) {
            emit(SearchResult.NoInternet)
        } else {
            val response = api.getUsers(UsersRepositoryImpl.USERS_QUANTITY)
            if (response.isSuccessful) {
                val usersWithBriefInfo = response.body()?.results?.map {
                    mapper.fromUserDtoToUserBriefInfo(it)
                }.orEmpty()

                emit(SearchResult.Success(usersWithBriefInfo))
            } else {
                emit(SearchResult.Error)
            }
        }
    }

    companion object {
        private const val BASE_RANDOM_USER_URL: String = "https://randomuser.me/api/"
    }
}