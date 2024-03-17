package com.example.usergenerator.presentation.users.state

import com.example.usergenerator.domain.models.UserBriefInfo

sealed interface UsersState {

    data class Content(val data: List<UserBriefInfo>) : UsersState

    data object FirstStart : UsersState

    data object Loading : UsersState

    data object ErrorNetwork : UsersState

    data object ErrorDatabase : UsersState

    data object NoInternet : UsersState
}