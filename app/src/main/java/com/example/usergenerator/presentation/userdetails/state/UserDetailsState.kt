package com.example.usergenerator.presentation.userdetails.state

import com.example.usergenerator.domain.models.UserDetails

sealed interface UserDetailsState {

    data class Content(val data: UserDetails) : UserDetailsState

    data object Loading : UserDetailsState

    data object Error : UserDetailsState
}