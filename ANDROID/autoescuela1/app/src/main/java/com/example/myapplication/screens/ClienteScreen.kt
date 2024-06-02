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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.Sujeto
import com.example.myapplication.models.SujetoViewModel
import com.example.myapplication.ui.theme.Screen

@Composable
fun ClienteScreen(navController: NavController, sujetoViewModel: SujetoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var DNI by remember { mutableStateOf("") }
    val sujetos = remember { mutableStateListOf<Sujeto>() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Unete A Nosotros",
            color = Color.Black,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Bienvenido a nuestra app de Autoescuela, porfavor ingrese sus datos pedidos y disfrute de nuestra experiencia como conductor.",
            color = Color.Black,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
        )

        OutlinedTextField(
            value = DNI,
            onValueChange = { DNI = it },
            label = { Text("DNI") },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (nombre != "" && apellido != "" && DNI != "") {
                        sujetoViewModel.anadirSujetos(nombre, apellido, DNI)
                    }
                    nombre = ""
                    apellido = ""
                    DNI = ""
                }
            ) {
                Text("Agregar Sujeto")
            }

            Button(
                onClick = {
                    navController.navigate(Screen.ClienteList)
                }
            ) {
                Text("Mostrar")
            }
        }

        sujetos.forEach { sujeto ->
            Text("Nombre: ${sujeto.nombre}, Apellido: ${sujeto.apellido}, DNI: ${sujeto.DNI}")
        }
    }
}

