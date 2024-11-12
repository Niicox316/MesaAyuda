package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.material.Card
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.clickable


// Composable principal para mostrar los casos de un usuario específico
@Composable
fun CheckStatusScreen(navController: NavHostController, nombreUsuario: String) {
    // Obtenemos la instancia de Firestore
    val db = FirebaseFirestore.getInstance()

    // Estado para almacenar los casos del usuario
    var casos by remember { mutableStateOf<List<Caso>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    var selectedCaso by remember { mutableStateOf<Caso?>(null) }

    // Cargar los casos para el usuario autenticado
    LaunchedEffect(nombreUsuario) {
        db.collection("cases")
            .whereEqualTo("nombreUsuario", nombreUsuario) // Filtrar casos por el nombreUsuario
            .get()
            .addOnSuccessListener { result ->
                casos = result.documents.mapNotNull { document ->
                    val caso = document.toObject(Caso::class.java)
                    // Imprimir los datos del caso para verificar si se están recuperando correctamente
                    Log.d("CheckStatus", "Caso recuperado: $caso")
                    caso // Si el caso es nulo, no lo añadimos a la lista
                }
                loading = false
            }
            .addOnFailureListener {
                errorMessage = "Error al obtener los casos."
                loading = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar el nombre del usuario (nombreUsuario)
        Text(
            text = "Nombre de usuario: $nombreUsuario",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Consulta de Estado de Casos",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Si está cargando, mostramos un indicador de progreso
        if (loading) {
            CircularProgressIndicator()
        } else if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        } else if (casos.isEmpty()) {
            Text(
                text = "No tienes casos registrados.",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            // Mostrar la lista de casos
            LazyColumn {
                items(casos) { caso ->
                    CasoItem(caso) {
                        selectedCaso = caso
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        // Si se seleccionó un caso, mostrar los detalles en un diálogo
        selectedCaso?.let { caso ->
            CasoDetailsDialog(caso, onDismiss = { selectedCaso = null })
        }
    }
}

// Composable para mostrar un solo caso en una tarjeta
@Composable
fun CasoItem(caso: Caso, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Agregar un pequeño margen alrededor de la tarjeta
            .clickable { onClick() }, // Manejar el clic en la tarjeta
        elevation = 4.dp // Aplica la sombra o elevación
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Espaciado interno del contenido de la tarjeta
                .fillMaxWidth()
        ) {
            // Mostrar los detalles de cada caso en la tarjeta
            Text(text = "Fecha: ${caso.fecha}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Descripción: ${caso.descripcion}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Estado: ${caso.estado}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Diálogo para mostrar los detalles de un caso seleccionado
@Composable
fun CasoDetailsDialog(caso: Caso, onDismiss: () -> Unit) {
    if (caso.fecha.isNotEmpty() && caso.descripcion.isNotEmpty() && caso.estado.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Detalles del Caso") },
            text = {
                Column {
                    Text(text = "Fecha: ${caso.fecha}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Descripción: ${caso.descripcion}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Estado: ${caso.estado}", style = MaterialTheme.typography.bodyMedium)
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar")
                }
            }
        )
    } else {
        Log.d("CheckStatus", "Datos vacíos en el caso: $caso")
    }
}

// Definición de la clase Caso con los campos correspondientes a la base de datos
data class Caso(
    val fecha: String = "",         // Cambiado 'date' a 'fecha'
    val descripcion: String = "",   // Cambiado 'description' a 'descripcion'
    val estado: String = "",        // Cambiado 'status' a 'estado'
    val nombreUsuario: String = ""  // El nombre de usuario que creó el caso
)

// Composable para la pantalla de previsualización
@Preview(showBackground = true)
@Composable
fun PreviewCheckStatusScreen() {
    val navController = rememberNavController() // Crear un navController en el preview
    CheckStatusScreen(navController = navController, nombreUsuario = "ejemplo@correo.com")
}

