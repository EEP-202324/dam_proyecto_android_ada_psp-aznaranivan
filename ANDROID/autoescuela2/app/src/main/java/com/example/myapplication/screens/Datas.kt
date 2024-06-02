package com.example.myapplication.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.myapplication.Rutas
import com.example.myapplication.ui.theme.AuthViewModel


@Composable
fun Datas(navController: NavController, viewModel: AuthViewModel = viewModel()) {

    val name by viewModel::name
    val DNI by viewModel::DNI
    val email by viewModel::email
    val password by viewModel::password

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Botón de retroceso en la esquina superior izquierda
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.popBackStack() } // Navegar a la pantalla anterior
                .align(Alignment.Start)
        )
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Table(
                        headers = listOf("Name", "DNI", "Email", "Password"),
                        data = listOf(name, DNI, email, "*".repeat(password.length))
                    )
                }
            }
        }

        Button(
            onClick = {
                try {
                    navController.navigate(Rutas.TodoListPage)
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error al navegar a TodoListPage", e)
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Ir a TodoListPage")
        }
    }
}

@Composable
fun Table(headers: List<String>, data: List<String>) {
    Column {
        // Fila para Name
        TableRow(listOf(headers[0]), true)
        Spacer(modifier = Modifier.height(16.dp))

        // Fila para DNI
        TableRow(listOf(headers[1]), true)
        Spacer(modifier = Modifier.height(16.dp))

        // Fila para Email
        TableRow(listOf(headers[2]), true)
        Spacer(modifier = Modifier.height(16.dp))

        // Fila para Password
        TableRow(listOf(headers[3]), true)
        Spacer(modifier = Modifier.height(16.dp))
    }
    Column {
        TableRow(listOf(data[0]), false)
        Spacer(modifier = Modifier.height(16.dp))

        TableRow(listOf(data[1]), false)
        Spacer(modifier = Modifier.height(16.dp))

        TableRow(listOf(data[2]), false)
        Spacer(modifier = Modifier.height(16.dp))

        TableRow(listOf(data[3]), false)

    }
}

@Composable
fun TableRow(items: List<String>, isHeader: Boolean) {
    items.forEachIndexed { index, item ->
        TableCell(item, isHeader, index == items.lastIndex)
    }
}

@Composable
fun TableCell(text: String, isHeader: Boolean, isLast: Boolean) {
    val cellColor = if (isHeader) {
        Color.DarkGray // Color de fondo para las celdas de encabezado
    } else {
        Color.LightGray // Color de fondo para las celdas de datos
    }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(cellColor)
            .border(1.dp, Color.Black) // Añadir borde a cada celda
            .padding(4.dp)
            .width(100.dp) // Ancho fijo para las celdas de datos
            .heightIn(min = 48.dp) // Altura mínima para las celdas
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal
            ),
            modifier = Modifier.padding(4.dp)
        )
        // Espaciador si no es la última celda para separarlas
        if (!isLast) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}