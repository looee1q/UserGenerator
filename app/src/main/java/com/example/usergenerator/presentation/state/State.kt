package com.example.usergenerator.presentation.state

sealed interface State<T> {

    data class Content<T>(val data: T) : State<T>

    class FirstStart<T> : State<T>

    class Loading<T> : State<T>

    class ErrorNetwork<T> : State<T>

    class ErrorDatabase<T> : State<T>

    class NoInternet<T> : State<T>
}