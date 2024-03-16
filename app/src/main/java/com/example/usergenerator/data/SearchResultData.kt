package com.example.usergenerator.data

import com.example.usergenerator.data.database.UserEntity

interface SearchResultData {

        data class Success(val data: List<UserEntity>) : SearchResultData

        data object Error : SearchResultData

        data object NoInternet : SearchResultData
}