package com.example.mesadeayuda2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mesadeayuda2.Entidades.Case
import com.example.mesadeayuda2.ViewModel.TechnicianCasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianCasesScreen(navController: NavHostController, viewModel: TechnicianCasesViewModel = viewModel()) {
    // Usamos collectAsState para observar el StateFlow de casos
    val cases by viewModel.cases.collectAsState(initial = emptyList())  // Lista vacía como valor inicial

    // Cargar los casos si no están disponibles
    LaunchedEffect(Unit) {
        viewModel.loadCases()  // Cargar los casos cuando la pantalla se inicie
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(Color(0xFF6200EE), Color(0xFFBB86FC))
            )
        )
        .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Casos del Técnico",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de casos
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(cases) { case -> // Recibe los objetos Case de la lista
                CaseItem(case, onEditClick = {
                    // Acción para cambiar el estado del caso
                    viewModel.updateCaseState(case)
                }) // Muestra cada caso en su Item correspondiente
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CaseItem(case: Case, onEditClick: () -> Unit) {
    // Establecer color según el estado del caso
    val backgroundColor = when (case.estado) {
        "Abierto" -> Color.Yellow
        "Pendiente" -> Color.Red
        "Resuelto" -> Color.Green
        else -> Color.Gray // color por defecto si no se encuentra el estado
    }

    // Estilo de las tarjetas de cada caso
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFF1F1F1)) // Fondo claro
        ) {
            // Mostrar la información del caso
            Text(
                text = "Caso: ${case.id}",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF6200EE) // Color atractivo
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Descripción: ${case.descripcion}", color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Estado: ${case.estado}", color = Color.Gray)

            // Botón de cambiar estado
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onEditClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Cambiar Estado", color = Color.White)
            }
        }
    }
}
