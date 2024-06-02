package com.example.myapplication.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.graphicsLayer

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
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.5f), // Ajusta el valor de alpha para cambiar la transparencia
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
            text = "Únete A Nosotros",
            color = Color.DarkGray,
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "PEÓN",
            color = Color.DarkGray,
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Text(
            text = "Conduce con seguridad",
            color = Color.DarkGray,
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Bienvenido a nuestra app de Autoescuela. Por favor, ingrese sus datos pedidos y disfrute de nuestra experiencia como conductor.",
            color = Color.DarkGray,
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(bottom = 80.dp)
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", style = TextStyle(color = Color.Black)) },
            textStyle = TextStyle(color = Color.Black)
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido", style = TextStyle(color = Color.Black)) },
            textStyle = TextStyle(color = Color.Black)
        )

        OutlinedTextField(
            value = DNI,
            onValueChange = { DNI = it },
            label = { Text("DNI", style = TextStyle(color = Color.Black)) },
            textStyle = TextStyle(color = Color.Black)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (nombre.isNotBlank() && apellido.isNotBlank() && DNI.isNotBlank()) {
                        sujetoViewModel.anadirSujetos(nombre, apellido, DNI)
                    }
                    nombre = ""
                    apellido = ""
                    DNI = ""
                }
            ) {
                Text("Añadir Sujeto", style = TextStyle(color = Color.Black))
            }

            Button(
                onClick = {
                    navController.navigate(Screen.ClienteList)
                }
            ) {
                Text("Siguiente", style = TextStyle(color = Color.Black))
            }
        }

        sujetos.forEach { sujeto ->
            Text(
                text = "Nombre: ${sujeto.nombre}, Apellido: ${sujeto.apellido}, DNI: ${sujeto.DNI}",
                color = Color.Green,
                style = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 18.sp)
            )
        }
    }
}