package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mesadeayuda2.R

@Composable
fun WelcomeScreen(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.imagenfondomenu)
    val logoImage = painterResource(id = R.drawable.logoinicio)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Imagen de fondo
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo centrado encima del título
            Image(
                painter = logoImage,
                contentDescription = "Logo de inicio",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            // Título de la pantalla
            Text(
                text = "Bienvenido a Mesa de Ayuda IT",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontSize = 40.sp
                ),
                modifier = Modifier
                    .padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Registrarse
            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Naranja
            ) {
                Icon(Icons.Filled.PersonAdd, contentDescription = "Registrar", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Registrarse", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Ingresar como usuario
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Naranja
            ) {
                Icon(Icons.Filled.Login, contentDescription = "Ingresar como usuario", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Usuario", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Ingresar como técnico de soporte
            Button(
                onClick = { navController.navigate("login_technician") },  // Cambié el destino a "login_technician"
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Naranja
            ) {
                Icon(Icons.Filled.Engineering, contentDescription = "Ingresar como técnico", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Técnico de Soporte", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Administrador
            Button(
                onClick = { navController.navigate("admin_login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Naranja
            ) {
                Icon(Icons.Filled.AdminPanelSettings, contentDescription = "Administrador", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Administrador", color = Color.White)
            }
        }
    }
}
