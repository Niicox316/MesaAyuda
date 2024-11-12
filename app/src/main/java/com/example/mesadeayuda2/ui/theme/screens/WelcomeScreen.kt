package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)),  // Fondo suave
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Mesa de Ayuda IT",
            modifier = Modifier.padding(16.dp),
            style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 24.sp)
        )

        // Botón Registrarse
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
        ) {
            Icon(Icons.Filled.PersonAdd, contentDescription = "Registrar", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Ingresar como usuario
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
        ) {
            Icon(Icons.Filled.Login, contentDescription = "Ingresar como usuario", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Ingresar como usuario", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Ingresar como técnico de soporte
        Button(
            onClick = { navController.navigate("technician_cases") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF673AB7))
        ) {
            Icon(Icons.Filled.Engineering, contentDescription = "Ingresar como técnico", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Ingresar como técnico de soporte", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Administrador
        Button(
            onClick = { navController.navigate("admin_login") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9C27B0))
        ) {
            Icon(Icons.Filled.AdminPanelSettings, contentDescription = "Administrador", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Administrador", color = Color.White)
        }
    }
}
