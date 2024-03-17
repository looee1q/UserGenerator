package com.example.usergenerator.presentation.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usergenerator.domain.models.DatabaseResult
import com.example.usergenerator.domain.usecase.GetUserDetailsByIdUseCase
import com.example.usergenerator.domain.usecase.OpenContactsAppUseCase
import com.example.usergenerator.domain.usecase.OpenEmailAppUseCase
import com.example.usergenerator.domain.usecase.OpenMapAppUseCase
import com.example.usergenerator.presentation.userdetails.state.UserDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserDetailsByIdUseCase: GetUserDetailsByIdUseCase,
    private val openContactsAppUseCase: OpenContactsAppUseCase,
    private val openEmailAppUseCase: OpenEmailAppUseCase,
    private val openMapAppUseCase: OpenMapAppUseCase
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

    fun openContactsApp(phone: String) {
        openContactsAppUseCase(phone)
    }

    fun openEmailApp(email: String) {
        openEmailAppUseCase(email)
    }

    fun openMapApp(latitude: String, longitude: String) {
        openMapAppUseCase(latitude, longitude)
    }

}