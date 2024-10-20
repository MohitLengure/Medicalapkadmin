package com.example.medicineadmin.UI_Layer


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medicineadmin.viewModel.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(userViewModel: UserViewModel = viewModel()) {
    val usersState by userViewModel.usersFlow.collectAsState()
    // Ensure 'usersState' is a list of 'getAllUserResponseItem'
    Scaffold(modifier = Modifier.padding(top = 90.dp, bottom = 100.dp))
    {
        LazyColumn {
            items(usersState) { user ->
                // Display each user in a card
                UserCard(user)
            }
        }

    }

}
