package com.example.MesaAyudaFinal.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush

@Composable
fun LoginSuccessScreen(
    username: String, // Recibe el nombre de usuario
    onRegisterCaseClick: () -> Unit,
    onCheckStatusClick: () -> Unit
) {
    // Fondo con gradiente de color
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6200EE), Color(0xFFBB86FC))
                )
            ), // Fondo con gradiente
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título "Bienvenido"
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White // Título en color blanco
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre de usuario destacado
        Text(
            text = username,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White // Nombre de usuario en color blanco
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón "Registrar Nuevo Caso"
        Button(
            onClick = onRegisterCaseClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(6.dp, shape = MaterialTheme.shapes.medium), // Sombra para resaltar
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF5722) // Fondo del botón en color naranja
            ),
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Registrar Nuevo Caso",
                tint = Color.White // Icono de color blanco
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Registrar Nuevo Caso",
                color = Color.White // Texto en blanco sobre fondo naranja
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Consultar Estado de Casos"
        Button(
            onClick = onCheckStatusClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(6.dp, shape = MaterialTheme.shapes.medium), // Sombra para resaltar
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF03A9F4) // Fondo del botón en color azul
            ),
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Consultar Estado de Casos",
                tint = Color.White // Icono de color blanco
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Consultar Estado de Casos",
                color = Color.White // Texto en blanco sobre fondo azul
            )
        }
    }
}
