package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
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

@Composable
fun LoginSuccessScreen(
    username: String, // Recibe el nombre de usuario
    onRegisterCaseClick: () -> Unit,
    onCheckStatusClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(MaterialTheme.colorScheme.background), // Fondo de pantalla
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de Bienvenida
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre el título y el nombre de usuario

        // Nombre de usuario
        Text(
            text = username,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciado entre el nombre de usuario y los botones

        // Botón para "Registrar Nuevo Caso" con estilo moderno
        Button(
            onClick = onRegisterCaseClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(8.dp, shape = MaterialTheme.shapes.medium), // Sombra más fuerte para dar más elevación
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Registrar Nuevo Caso",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Registrar Nuevo Caso",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre los botones

        // Botón para "Consultar Estado de Casos" con estilo moderno
        Button(
            onClick = onCheckStatusClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(8.dp, shape = MaterialTheme.shapes.medium), // Sombra más fuerte para dar más elevación
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Consultar Estado de Casos",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Consultar Estado de Casos",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}
