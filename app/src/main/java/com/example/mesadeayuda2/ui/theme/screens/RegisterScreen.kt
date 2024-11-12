package com.example.mesadeayuda2.ui.theme.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterScreen(navController: NavController) {
    // Variables para los campos de entrada
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()

    // Cargar la imagen de fondo
    val backgroundImage: Painter = painterResource(id = com.example.mesadeayuda2.R.drawable.fondo_menu)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Fondo blanco de respaldo para evitar problemas con la imagen
    ) {
        // Imagen de fondo que ocupa toda la pantalla
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo de menú",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título de la pantalla con color que contraste con el fondo blanco
            Text(
                text = "Registro de Usuario",
                color = Color(0xFF6200EE), // Color morado
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 32.dp) // Espacio entre el título y los campos
            )

            // Campo de Nombre de Usuario con bordes morados
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de Usuario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Espacio entre campos
                shape = RoundedCornerShape(16.dp), // Bordes redondeados
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF6200EE),
                    cursorColor = Color(0xFF6200EE)
                )
            )

            // Campo de Correo Electrónico con bordes morados
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF6200EE),
                    cursorColor = Color(0xFF6200EE)
                )
            )

            // Campo de Contraseña con icono para mostrar u ocultar la contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Mostrar Contraseña",
                            tint = Color(0xFF6200EE) // Color del icono
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6200EE),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF6200EE),
                    cursorColor = Color(0xFF6200EE)
                )
            )

            // Botón de Registro con color de fondo vibrante
            Button(
                onClick = { registerUser(context, username, email, password, navController) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)), // Color de fondo del botón
                shape = RoundedCornerShape(16.dp), // Bordes redondeados
            ) {
                Text(
                    text = "Registrar",
                    color = Color.White, // Texto en blanco para contrastar con el botón
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

private fun registerUser(context: Context, username: String, email: String, password: String, navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()

    // Crear un mapa de datos del usuario
    val userData = hashMapOf(
        "username" to username,
        "email" to email,
        "password" to password // En producción, no debes almacenar la contraseña directamente
    )

    // Agregar datos a Firestore
    firestore.collection("users")
        .add(userData)
        .addOnSuccessListener {
            Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            navController.popBackStack() // Volver a la pantalla anterior
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, "Error al registrar el usuario: ${e.message}", Toast.LENGTH_SHORT).show()
        }
}
