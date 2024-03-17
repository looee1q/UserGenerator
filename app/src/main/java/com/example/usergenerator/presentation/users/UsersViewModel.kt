package com.example.usergenerator.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.usergenerator.domain.usecase.GetUsersFromNetworkUseCase
import com.example.usergenerator.presentation.users.state.UsersState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersFromNetworkUseCase: GetUsersFromNetworkUseCase,
    private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<UsersState>(UsersState.FirstStart)
    val stateLiveData: LiveData<UsersState> get() = _stateLiveData

    init {
        getUsersFromDatabase()
    }

    fun getUsersFromNetwork() {
        _stateLiveData.postValue(UsersState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getUsersFromNetworkUseCase()
                .catch {
                    emit(SearchResult.ErrorDatabase)
                }
                .collect {
                when (it) {
                    SearchResult.NoInternet -> _stateLiveData.postValue(UsersState.NoInternet)
                    SearchResult.ErrorNetwork -> _stateLiveData.postValue(UsersState.ErrorNetwork)
                    SearchResult.ErrorDatabase -> _stateLiveData.postValue(UsersState.ErrorDatabase)
                    is SearchResult.Success -> {
                        if (it.data.isEmpty()) {
                            _stateLiveData.postValue(UsersState.ErrorNetwork)
                        } else {
                            _stateLiveData.postValue(UsersState.Content(it.data))
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
                            _stateLiveData.postValue(UsersState.FirstStart)
                        } else {
                            _stateLiveData.postValue(UsersState.Content(it.data))
                        }
                    }
                    is DatabaseResult.Error -> _stateLiveData.postValue(UsersState.ErrorDatabase)
                }
            }
        }
    }
}