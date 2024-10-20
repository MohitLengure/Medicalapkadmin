package com.example.medicineadmin.UI_Layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicineadmin.network.response.getAllUserResponseItem



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicineadmin.viewModel.UserViewModel

@Composable
fun UserCard(user: getAllUserResponseItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(text = "ID: ${user.id}", style = MaterialTheme.typography.titleSmall)
            Text(text = "User Id: ${user.user_id}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Name: ${user.name}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Phone: ${user.phone_number}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Level: ${user.level}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Address: ${user.address}", style = MaterialTheme.typography.titleSmall)
            Text(text = "PinCode: ${user.pinCode}", style = MaterialTheme.typography.titleSmall)

            if(user.isApproved ==0)
            {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Approve")
                }
            }
            else
            {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Disapprove")
                }
            }
            // Add other fields as necessary
        }
    }
}

