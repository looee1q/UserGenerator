package com.example.usergenerator.di

import com.example.usergenerator.presentation.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(usersNetworkUseCase = get())
    }
}