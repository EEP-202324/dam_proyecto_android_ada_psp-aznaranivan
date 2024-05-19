package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.AuthViewModel
import com.example.myapplication.R
import com.example.myapplication.Rutas

@Composable
fun SignUp(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    val name by viewModel::name
    val DNI by viewModel::DNI
    val email by viewModel::email
    val password by viewModel::password

    var nameValue by remember { mutableStateOf(name) }
    var DNIValue by remember { mutableStateOf(DNI) }
    var emailValue by remember { mutableStateOf(email) }
    var passwordValue by remember { mutableStateOf(password) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Restablecer los datos
        Button(
            onClick = {
                viewModel.resetCredentials()
                nameValue = ""
                DNIValue = ""
                emailValue = ""
                passwordValue = ""
            },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        ) {
            Text(text = "Reset")
        }

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Login image",
            modifier = Modifier.size(200.dp)
        )

        Text(text = "Welcome", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Create your account")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nameValue,
            onValueChange = {
                nameValue = it
                viewModel.name = it
            },
            label = { Text(text = "First Name and Surname") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = DNIValue,
            onValueChange = {
                DNIValue = it
                viewModel.DNI = it
            },
            label = { Text(text = "DNI") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = emailValue,
            onValueChange = {
                emailValue = it
                viewModel.email = it
            },
            label = { Text(text = "Email Address") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordValue,
            onValueChange = {
                passwordValue = it
                viewModel.password = it
            },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Rutas.loginscreen)
        }) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Forgot Password?", modifier = Modifier.clickable { })
    }
}