package com.example.usergenerator.di

import com.example.usergenerator.domain.network.UsersNetworkUseCase
import com.example.usergenerator.domain.network.UsersNetworkUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<UsersNetworkUseCase> {
        UsersNetworkUseCaseImpl(usersNetworkRepository = get())
    }
}