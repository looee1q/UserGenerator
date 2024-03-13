package com.example.usergenerator.domain.models

sealed interface SearchResult {

    data class Success(val data: List<UserBriefInfo>) : SearchResult

    data object Error : SearchResult

    data object NoInternet : SearchResult
}