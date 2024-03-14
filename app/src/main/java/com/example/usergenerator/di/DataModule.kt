package com.example.usergenerator.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.usergenerator.data.mapper.Mapper
import com.example.usergenerator.data.network.NetworkClient
import com.example.usergenerator.data.network.RetrofitClient
import com.example.usergenerator.data.repository.UsersRepositoryImpl
import com.example.usergenerator.domain.repository.UsersRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single<NetworkClient> {
        RetrofitClient(
            connectivityManager = get(),
            mapper = get()
        )
    }

    single {
        Mapper()
    }

    single<UsersRepository> {
        UsersRepositoryImpl(
            networkClient = get()
        )
    }
}