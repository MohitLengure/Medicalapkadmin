package com.example.medicineadmin.UI_Layer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medicineadmin.viewModel.UserViewModel


@Composable
fun AddProduct(viewModel: UserViewModel = hiltViewModel()) {

    val state = viewModel.addProductState.collectAsState()

    when {
        state.value.Loading -> {
            Box(modifier = androidx.compose.ui.Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
            {
                CircularProgressIndicator()
            }
        }
        state.value.Error != null -> {
            Box()
            {
                Text(text = state.value.Error.toString())
            }
        }
        state.value.Data != null -> {
            Log.d("Message","${state.value.Data?.body()?.message}")
            Toast.makeText(LocalContext.current, "${state.value.Data?.body()?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    val context = LocalContext.current

    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var expireDate by remember { mutableStateOf("") }

/*    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())*/

/*// Expiry Date Picker
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            expireDate = calendar.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )*/

    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
        ,verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input fields
        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") }
        )

        OutlinedTextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("Product Price") }
        )

        OutlinedTextField(
            value = productCategory,
            onValueChange = { productCategory = it },
            label = { Text("Product Category") }
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock") }
        )

        OutlinedTextField(
            value = expireDate,
            onValueChange = { expireDate = it },
            label = { Text("ExpireDate") }
        )

        // Expiry date button
      /*  Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Select Expiry Date: ${dateFormat.format(expireDate)}")
        }
*/
        // Submit button
        Button(
            onClick = {
                if (productName.isNotEmpty() && productPrice.isNotEmpty() && productCategory.isNotEmpty() && stock.isNotEmpty()) {
                    viewModel.addProduct(
                        ProductName = productName,
                        ProductPrice = productPrice,
                        ProductCategory = productCategory,
                        Stock = stock.toInt(),
                        Expiredate = expireDate
                    )

                }
                else{
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Add Product")
        }
    }
}