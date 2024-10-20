package com.example.medicineadmin.UI_Layer


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicineadmin.viewModel.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: UserViewModel = hiltViewModel()) {

    // Collect the list of users from the ViewModel
    val usersState by viewModel.usersFlow.collectAsState()
    val context = LocalContext.current
    val state = viewModel.updateUserState.collectAsState()

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    when {
        state.value.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.value.Error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.value.Error.toString())
            }
        }
        state.value.Data != null -> {
            Toast.makeText(context, "${state.value.Data?.body()?.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Ensure 'usersState' is a list of 'getAllUserResponseItem'
    Scaffold(modifier = Modifier.padding(top = 90.dp, bottom = 100.dp)) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                scope.launch {
                    isRefreshing = true
                    viewModel.refreshUsers() // Assuming this function refreshes the user list
                    delay(3000L) // Simulate network delay
                    isRefreshing = false
                }
            }
        ) {
            LazyColumn {
                items(usersState) { user ->
                    // Display each user in a card
                    UserCard(user,
                        onClickApprove = {
                            viewModel.approveUser(userId = user.user_id, isApproved = 1)
                        }
                    )
                }
            }
        }
    }
}