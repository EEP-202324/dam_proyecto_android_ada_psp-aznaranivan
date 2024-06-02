package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.AutoescuelaList
import com.example.myapplication.screens.ClienteList
import com.example.myapplication.screens.ClienteScreen
import com.example.myapplication.ui.theme.Screen


@Composable
fun RutasScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ClienteScreen
    ) {
        composable(Screen.ClienteScreen) {
            ClienteScreen(navController, viewModel())
        }
        composable(Screen.ClienteList) {
            ClienteList(navController)
        }
        composable(Screen.AutoescuelaList) {
            AutoescuelaList(navController)
        }
    }
}