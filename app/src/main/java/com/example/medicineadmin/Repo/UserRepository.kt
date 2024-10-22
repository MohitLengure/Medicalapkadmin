package com.example.medicineadmin.Repo

    import com.example.medicineadmin.State
    import com.example.medicineadmin.network.ApiProvider
    import com.example.medicineadmin.network.apiServices
    import com.example.medicineadmin.network.response.UpdateUserResponse
    import com.example.medicineadmin.network.response.addproductResponse
    import com.example.medicineadmin.network.response.getAllUserResponseItem

    import kotlinx.coroutines.flow.Flow
    import kotlinx.coroutines.flow.flow
    import retrofit2.Response
    import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: apiServices // Inject your ApiService
) {
    // Fetch users and return a flow of List<getAllUserResponseItem>
    suspend fun getAllUsers(): Flow<List<getAllUserResponseItem>> = flow {
        try {
            // Assuming you use Retrofit to make the API call
            val response =
                apiService.getAllUsers() // Make sure getAllUsers() returns getAllUserResponse
            if (response.isSuccessful) {
                // Emit the list of users (List<getAllUserResponseItem>)
                emit(response.body() ?: emptyList())
            } else {
                // Emit an empty list if the response is not successful
                emit(emptyList())
            }
        } catch (e: Exception) {
            // Handle errors and emit an empty list in case of failure
            emit(emptyList())
        }
    }


    suspend fun approvedUser(
        userId: String,
        isApproved: Int
    ): Flow<State<Response<UpdateUserResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.apiProvider()
                .updateUserAllDetails(user_id = userId, isApproved = isApproved)
        } catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }


    suspend fun addProduct(
        productName: String,
        productPrice: String,
        productCategory: String,
        stock: Int,
        expiredate: String
    ): Flow<State<Response<addproductResponse>>> = flow {
        emit(State.Loading)
        try {
            val response = ApiProvider.apiProvider().addProduct(
                Product_Name = productName,
                Product_Price = productPrice,
                Product_Category = productCategory,
                Stock = stock,
                Expire_date = expiredate
            )
            emit(State.Success(response))
        }catch (e: Exception) {
            emit(State.Error(e.message.toString()))
        }
    }
}



