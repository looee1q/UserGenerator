package com.example.usergenerator.presentation.state

sealed interface State<T> {

    data class Content<T>(val data: T) : State<T>

    class Empty<T> : State<T>

    class Loading<T> : State<T>

    class Error<T> : State<T>

    class NoInternet<T> : State<T>
}