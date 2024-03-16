package com.example.usergenerator.di

import com.example.usergenerator.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.usergenerator.domain.usecase.GetUsersFromDatabaseUseCaseImpl
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCase
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<GetUsersFromNetworkUseCase> {
        GetUsersFromNetworkUseCaseImpl(usersRepository = get())
    }

    factory<GetUsersFromDatabaseUseCase> {
        GetUsersFromDatabaseUseCaseImpl(usersRepository = get())
    }
}