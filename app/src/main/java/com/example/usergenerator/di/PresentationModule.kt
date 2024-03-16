package com.example.usergenerator.di

import com.example.usergenerator.domain.usecase.GetUserDetailsByIdUseCase
import com.example.usergenerator.domain.usecase.GetUserDetailsByIdUseCaseImpl
import com.example.usergenerator.presentation.userdetails.UserDetailsViewModel
import com.example.usergenerator.presentation.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(
            getUsersFromNetworkUseCase = get(),
            getUsersFromDatabaseUseCase = get()
        )
    }

    viewModel<UserDetailsViewModel> {
        UserDetailsViewModel(
            getUserDetailsByIdUseCase = get()
        )
    }
}