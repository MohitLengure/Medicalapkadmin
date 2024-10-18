package com.example.medicaladmin.network

import com.example.medicaladmin.network.response.getallusersItem
import retrofit2.Response
import retrofit2.http.GET

interface apiservice {

    @GET("getAllUsers")
    suspend fun getAllUsers(): Response<getallusersItem>


}