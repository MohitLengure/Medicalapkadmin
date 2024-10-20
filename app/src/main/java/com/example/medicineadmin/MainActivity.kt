package com.example.medicineadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicineadmin.UI_Layer.HomeScreen
import com.example.medicineadmin.UI_Layer.MainScreen
import com.example.medicineadmin.ui.theme.MedicineAdminTheme
import com.example.medicineadmin.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicineAdminTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val userViewModel: UserViewModel = hiltViewModel()
                    MainScreen()
                }
            }
        }
    }
}

