package com.example.medicineadmin

sealed class Result<out T> {
    object Loading : Result<Nothing>() // Represents loading Result
    data class Success<out T>(val data: T) : Result<T>() // Represents success with data
    data class Error(val exception: Throwable) : Result<Nothing>() // Represents error Result
}
