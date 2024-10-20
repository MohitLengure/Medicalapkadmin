package com.example.medicineadmin.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: apiServices = retrofit.create(apiServices::class.java)


}