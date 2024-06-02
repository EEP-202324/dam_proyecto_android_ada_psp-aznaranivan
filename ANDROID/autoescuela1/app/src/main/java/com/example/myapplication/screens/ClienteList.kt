package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.models.ListViewModel
import com.example.myapplication.ui.theme.Screen

import com.example.myapplication.R

@Composable
fun ClienteList(navController: NavController, modifier: Modifier = Modifier) {
    val listViewModel: ListViewModel = viewModel()
    listViewModel.recuperarSujetos()
    val state = listViewModel.sujetoUiState
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        when (state) {
            is ListViewModel.SujetoUiState.Error -> {
                Text(text = "Error")
            }
            is ListViewModel.SujetoUiState.Loading -> {
                Text(text = "waiting")
            }
            is ListViewModel.SujetoUiState.Success -> {
                val listaSujetos = state.sujetos
                LazyColumn(modifier = modifier) {
                    items(listaSujetos) { p ->
                        UserProfileCard(
                            id = p.id,
                            Name = p.nombre,
                            lastName = p.apellido,
                            DNI = p.DNI,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    StandardFab(navController = navController)
                }
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    StandardFab2(navController)
                }
            }
        }
    }
}
@Composable
fun StandardFab(navController: NavController, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { navController.navigate(Screen.ClienteScreen) },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = "Añadir Usuario")
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }
}


@Composable
fun StandardFab2(navController: NavController, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {navController.navigate(Screen.AutoescuelaList)  },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.error

    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = "Lista de Escuelas ")
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Add"
            )
        }
    }
}
@Composable
fun UserProfileCard(id: Int, Name: String, lastName: String, DNI: String) {
    var viewModel: ListViewModel = viewModel()
    Surface(
        modifier = Modifier
            .height(210.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentSize(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFFD1F5E1),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        text = "Ficha de Usuario $id",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Nombre : $Name $lastName",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Usuario Aula")

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "DNI $DNI",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row (){
                    OutlinedButton(
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.Transparent
                        ),
                        onClick = {
                            viewModel.borrarSujeto(id)
                        }
                    ) {
                        Text(
                            text = "Borrar Usuario",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    OutlinedButton(
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Blue,
                            containerColor = Color.White,

                            ),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = "Seleccion Autoescuela",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prew() {
    UserProfileCard(1,"Ivan", "Maurin", "34567871L")
}