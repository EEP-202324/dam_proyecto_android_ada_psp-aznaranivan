package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.db.TodoListPage
import com.example.myapplication.screens.Datas
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.SignUp
import com.example.myapplication.ui.theme.AuthViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()


                    NavHost(navController = navController, startDestination = Rutas.signup) {
                        composable(Rutas.signup) {
                            SignUp(navController = navController, viewModel = authViewModel)
                        }
                        composable(Rutas.loginscreen) {
                            LoginScreen(navController = navController, viewModel = authViewModel)
                        }
                        composable(Rutas.datas) {
                            Datas(navController = navController, viewModel = authViewModel)
                        }
                        composable(Rutas.TodoListPage) {
                            TodoListPage(navController = navController, viewModel = authViewModel)
                        }
                    }
                }
            }
        }
    }
}