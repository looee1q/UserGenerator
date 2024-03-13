package com.example.usergenerator.presentation.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usergenerator.domain.models.SearchResult
import com.example.usergenerator.domain.models.UserBriefInfo
import com.example.usergenerator.domain.network.UsersNetworkUseCase
import com.example.usergenerator.presentation.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersNetworkUseCase: UsersNetworkUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<State<List<UserBriefInfo>>>()
    val stateLiveData: LiveData<State<List<UserBriefInfo>>> get() = _stateLiveData

    fun getUsers() {
        _stateLiveData.postValue(State.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            usersNetworkUseCase.getRandomUsers().collect {
                when (it) {
                    SearchResult.NoInternet -> _stateLiveData.postValue(State.NoInternet())
                    SearchResult.Error -> _stateLiveData.postValue(State.Error())
                    is SearchResult.Success -> {
                        if (it.data.isEmpty()) {
                            _stateLiveData.postValue(State.Empty())
                        } else {
                            _stateLiveData.postValue(State.Content(it.data))
                            Log.d("UsersViewModel", "Users2 are ${it.data}")
                        }
                    }
                }
            }
        }
    }
}