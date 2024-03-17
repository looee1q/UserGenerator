package com.example.usergenerator.di

import com.example.usergenerator.domain.usecase.GetUserDetailsByIdUseCase
import com.example.usergenerator.domain.usecase.implementation.GetUserDetailsByIdUseCaseImpl
import com.example.usergenerator.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.usergenerator.domain.usecase.implementation.GetUsersFromDatabaseUseCaseImpl
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCase
import com.example.usergenerator.domain.usecase.OpenContactsAppUseCase
import com.example.usergenerator.domain.usecase.OpenEmailAppUseCase
import com.example.usergenerator.domain.usecase.OpenMapAppUseCase
import com.example.usergenerator.domain.usecase.implementation.GetUsersFromNetworkUseCaseImpl
import com.example.usergenerator.domain.usecase.implementation.OpenContactsAppUseCaseImpl
import com.example.usergenerator.domain.usecase.implementation.OpenEmailAppUseCaseImpl
import com.example.usergenerator.domain.usecase.implementation.OpenMapAppUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<GetUsersFromNetworkUseCase> {
        GetUsersFromNetworkUseCaseImpl(usersRepository = get())
    }

    factory<GetUsersFromDatabaseUseCase> {
        GetUsersFromDatabaseUseCaseImpl(usersRepository = get())
    }

    factory<GetUserDetailsByIdUseCase> {
        GetUserDetailsByIdUseCaseImpl(usersRepository = get())
    }

    factory<OpenContactsAppUseCase> {
        OpenContactsAppUseCaseImpl(externalNavigationRepository = get())
    }

    factory<OpenEmailAppUseCase> {
        OpenEmailAppUseCaseImpl(externalNavigationRepository = get())
    }

    factory<OpenMapAppUseCase> {
        OpenMapAppUseCaseImpl(externalNavigationRepository = get())
    }
}