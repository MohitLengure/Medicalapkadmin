package com.example.medicineadmin.network

import com.example.medicineadmin.network.response.UpdateUserResponse
import com.example.medicineadmin.network.response.getAllUserResponse
import com.example.medicineadmin.network.response.getAllUserResponseItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface apiServices {

    @GET("getAllUsers")
    suspend fun getAllUsers(): Response<List<getAllUserResponseItem>>



    @FormUrlEncoded
    @PATCH("updateUserName")
    suspend fun updateUserAllDetails(
        @Field("userID") user_id: String,
        @Field("isApproved") isApproved: Int,
    ): Response<UpdateUserResponse>



}