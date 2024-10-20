package com.example.medicineadmin.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineadmin.Repo.UserRepository
import com.example.medicineadmin.State
import com.example.medicineadmin.network.response.UpdateUserResponse
import com.example.medicineadmin.network.response.getAllUserResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _usersFlow = MutableStateFlow<List<getAllUserResponseItem>>(emptyList())
    val usersFlow = _usersFlow.asStateFlow()

    private val _updateUserState = MutableStateFlow(UpdateUserState())
    val updateUserState = _updateUserState.asStateFlow()


    init {
        getAllUsers()  // Call to load users on initialization
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect { users ->
                _usersFlow.value = users  // Update state with the list of users
                delay(3000L)

            }
        }
    }


    fun approveUser(userId: String, isApproved: Int) {
        viewModelScope.launch {
            userRepository.approvedUser(userId, isApproved).collect {
                when(it)
                {
                    is State.Loading -> {
                        _updateUserState.value = UpdateUserState(Loading = true)
                    }
                    is State.Success -> {
                        _updateUserState.value = UpdateUserState(Data = it.data)
                }
                    is State.Error -> {
                        _updateUserState.value = UpdateUserState(Error = it.message)
                    }
                }
            }
        }
    }

    fun refreshUsers() {
        getAllUsers()
    }


}

data class UpdateUserState(
    val Loading: Boolean = false,
    val Error: String? = null,
    val Data: Response<UpdateUserResponse>? = null
)