package com.example.medicaladmin.repo

import androidx.compose.runtime.State
import com.example.medicaladmin.network.apiprovider
import com.example.medicaladmin.network.response.getallusers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.Response

class Repo {

    suspend fun getAllUsers(): Flow<State<Response<getallusers>>> = flow{
        emit(State.Loading)
        try{
            val response = apiprovider.provideApi().getAllUsers()
            emit(State.Success(response))
        }catch (e: Exception){
            emit(State.Error(e.message.toString()))
        }
    }

}