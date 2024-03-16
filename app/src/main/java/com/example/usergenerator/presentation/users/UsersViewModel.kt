package com.example.usergenerator.presentation.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCase
import com.example.usergenerator.presentation.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersFromNetworkUseCase: GetUsersFromNetworkUseCase,
    private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<State<List<UserBriefInfo>>>(State.FirstStart())
    val stateLiveData: LiveData<State<List<UserBriefInfo>>> get() = _stateLiveData

    init {
        getUsersFromDatabase()
    }

    fun getUsersFromNetwork() {
        _stateLiveData.postValue(State.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            getUsersFromNetworkUseCase().collect {
                when (it) {
                    SearchResult.NoInternet -> _stateLiveData.postValue(State.NoInternet())
                    SearchResult.Error -> _stateLiveData.postValue(State.ErrorNetwork())
                    is SearchResult.Success -> {
                        if (it.data.isEmpty()) {
                            _stateLiveData.postValue(State.ErrorNetwork())
                        } else {
                            _stateLiveData.postValue(State.Content(it.data))
                            Log.d("UsersViewModel", "Users2 are ${it.data.map { it.firstName + it.lastName }}}")
                        }
                    }
                }
            }
        }
    }

    private fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsersFromDatabaseUseCase()
                .catch {
                    emit(DatabaseResult.Error())
                }
                .collect {
                when (it) {
                    is DatabaseResult.Success -> {
                        if (it.data.isEmpty()) {
                            _stateLiveData.postValue(State.FirstStart())
                        } else {
                            _stateLiveData.postValue(State.Content(it.data))
                        }
                    }
                    is DatabaseResult.Error -> _stateLiveData.postValue(State.ErrorDatabase())
                }
            }
        }
    }
}