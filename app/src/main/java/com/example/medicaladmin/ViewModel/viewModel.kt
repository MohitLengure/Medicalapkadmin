package com.example.medicaladmin.ViewModel

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicaladmin.State
import com.example.medicaladmin.network.response.getallusers
import com.example.medicaladmin.repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class viewModel @Inject constructor(private val repo: Repo) : ViewModel() {


    private val _getAllUserState= MutableStateFlow(GetAllUserState)

    val getAllUserState = _getAllUserState.asStateFlow()

    init {
        getAllUsers()
    }
    fun getAllUsers(){
        viewModelScope.launch {state ->
            when(state)
            {
                is State.Loading -> {
                    _getAllUserState.value = GetAllUserState(Loading = true)
                }
                is State.Success -> {
                    _getAllUserState.value = GetAllUserState(Data=state.data,Loading = false)
                }
                is State.Error -> {
                    _getAllUserState.value = GetAllUserState(Error = state.message,Loading = false)
                }
            }

        }
    }



}



data class GetAllUserState(
    val Loading:Boolean=false,
    val Error:String?=null,
    val Data: Response<getallusers>?=null

)