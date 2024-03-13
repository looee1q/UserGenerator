package com.example.usergenerator.di

import com.example.usergenerator.data.network.UsersNetworkRepositoryImpl
import com.example.usergenerator.domain.network.UsersNetworkRepository
import org.koin.dsl.module

val dataModule = module {

    single<UsersNetworkRepository> {
        UsersNetworkRepositoryImpl(context = get())
    }
}