package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TechnicianCasesScreen() {
    // Pantalla en blanco con mensaje centrado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Casos",
            color = Color.Black,
            modifier = Modifier.padding(16.dp),
            style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
        )
    }
}