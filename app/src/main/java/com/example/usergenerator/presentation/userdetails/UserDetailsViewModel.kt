package com.example.usergenerator.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.usecase.GetUserDetailsByIdUseCase
import com.example.usergenerator.presentation.userdetails.state.UserDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserDetailsByIdUseCase: GetUserDetailsByIdUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<UserDetailsState>()
    val stateLiveData: LiveData<UserDetailsState> get() = _stateLiveData

    fun getUserDetailsById(userId: Int) {
        _stateLiveData.postValue(UserDetailsState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            getUserDetailsByIdUseCase(userId)
                .catch {
                    emit(DatabaseResult.Error())
                }
                .collect {
                when (it) {
                    is DatabaseResult.Success -> {
                        _stateLiveData.postValue(UserDetailsState.Content(it.data))
                    }
                    is DatabaseResult.Error -> {
                        _stateLiveData.postValue(UserDetailsState.Error)
                    }
                }
            }
        }
    }

}