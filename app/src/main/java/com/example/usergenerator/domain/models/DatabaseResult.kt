package com.example.usergenerator.domain.models

sealed interface DatabaseResult<T> {

    data class Success<T>(val data: T) : DatabaseResult<T>

    class Error<T> : DatabaseResult<T>
}