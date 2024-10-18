package com.example.medicaladmin.di

import androidx.compose.ui.tooling.preview.Preview
import com.example.medicaladmin.repo.Repo

object DataModule {

    @Singleton
    @Provides

    fun provideRepo()= Repo()

}