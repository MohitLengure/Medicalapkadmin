package com.example.medicineadmin.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicineadmin.Repo.UserRepository
import com.example.medicineadmin.network.response.getAllUserResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _usersFlow = MutableStateFlow<List<getAllUserResponseItem>>(emptyList())
    val usersFlow = _usersFlow.asStateFlow()

    init {
        getAllUsers()  // Call to load users on initialization
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect { users ->
                _usersFlow.value = users  // Update state with the list of users
            }
        }
    }
}