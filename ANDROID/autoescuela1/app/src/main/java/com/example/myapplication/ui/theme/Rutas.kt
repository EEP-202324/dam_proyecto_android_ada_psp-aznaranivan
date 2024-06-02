package com.example.myapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.RutasScreen

@Composable
fun Rutas(navController: NavHostController = rememberNavController()) {
    RutasScreen(navController = navController)
}
object Screen{
    val ClienteScreen = "ClienteScreen"
    val ClienteList = "ClienteList"
    val AutoescuelaList = "AutoescuelaList"
}