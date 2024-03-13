package com.example.usergenerator.data.network

import com.example.usergenerator.data.dto.UsersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET(".")
    suspend fun getUsers(@Query("results") results: Int): Response<UsersResponseDto>
}