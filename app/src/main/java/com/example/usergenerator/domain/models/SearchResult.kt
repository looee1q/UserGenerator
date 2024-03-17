package com.example.usergenerator.domain.models

sealed interface SearchResult {

    data class Success(val data: List<UserBriefInfo>) : SearchResult

    data object ErrorNetwork : SearchResult

    data object ErrorDatabase : SearchResult

    data object NoInternet : SearchResult
}