package com.example.medicineadmin.di

import com.example.medicineadmin.Repo.UserRepository
import com.example.medicineadmin.network.BASE_URL
import com.example.medicineadmin.network.apiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // or other appropriate component scope
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): apiServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Use your API base URL
            .addConverterFactory(GsonConverterFactory.create()) // or another converter
            .build()
            .create(apiServices::class.java)
    }

  /*  @Provides
    @Singleton
    fun provideUserRepository(apiService: apiServices): UserRepository {
        return UserRepository(apiService)
    }*/
}

