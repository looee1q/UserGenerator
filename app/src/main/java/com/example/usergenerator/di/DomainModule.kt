package com.example.usergenerator.di

import com.example.usergenerator.domain.usecase.GetUsersUseCase
import com.example.usergenerator.domain.usecase.GetUsersUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<GetUsersUseCase> {
        GetUsersUseCaseImpl(usersRepository = get())
    }
}