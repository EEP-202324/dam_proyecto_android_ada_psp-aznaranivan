package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    val email by viewModel::email
    val password by viewModel::password

    var emailValue by remember { mutableStateOf(email) }
    var passwordValue by remember { mutableStateOf(password) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Botón de retroceso en la esquina superior izquierda
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.popBackStack() } // Navegar a la pantalla anterior
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    viewModel.resetCredentials()
                    emailValue = ""
                    passwordValue = ""
                },
                modifier = Modifier
                    .align(Alignment.End) // Cambiado a Alignment.End
                    .padding(16.dp)
            ) {
                Text(text = "Reset")
            }
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Login image",
                modifier = Modifier.size(200.dp)
            )

            Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Login to your account")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = emailValue, onValueChange = {
                    emailValue = it
                    viewModel.email = it // Actualizar el valor en el ViewModel
                }, label = {
                    Text(text = "Email address")
                })

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = passwordValue,
                onValueChange = {
                    passwordValue = it
                    viewModel.password = it // Actualizar el valor en el ViewModel
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate(Rutas.datas)
            }) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Forgot Password?", modifier = Modifier.clickable {

            })

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Or sign in with")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            //Facebook click
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            //Google click
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            //Twitter click
                        }
                )
            }
        }
    }
}