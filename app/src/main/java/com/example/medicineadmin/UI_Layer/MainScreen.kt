package com.example.medicineadmin.UI_Layer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicineadmin.data.NavItem
import com.example.medicineadmin.viewModel.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen()
{
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())





    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home,0),
        NavItem("Approved Prodcut", Icons.Default.ShoppingCart,0),
        NavItem("Notifications", Icons.Default.Notifications,5),
        NavItem("Add Product", Icons.Default.AddCircle,0),
        )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(modifier = Modifier.height(80.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF11BD28), titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { /* do somet

                    hing */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Admin Panel",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        fontFamily = SansSerif
                    )
                },

                scrollBehavior = scrollBehavior
            )
        })
    {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    navItemList.forEachIndexed{
                            index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex==index,
                            onClick = {
                                selectedIndex=index
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if(navItem.badgeCount>0)
                                        Badge(){
                                            Text(text = navItem.badgeCount.toString())
                                        }
                                }) {
                                    Icon(imageVector = navItem.icon, contentDescription =null )
                                }

                            },
                            label = {
                                Text(text = navItem.label)
                            }

                        )
                    }
                }
            }

        ) { innerPadding ->
            ContentScreen(modifier= Modifier.padding(innerPadding),selectedIndex)
        }


    }

}



@Composable
fun ContentScreen(modifier: Modifier=Modifier,selectedIndex:Int)
{
    when(selectedIndex)
    {
        0-> HomeScreen()
        1-> ProductApproved()
        2->Notification()
        3-> AddProduct()
    }
}