package com.example.mesadeayuda2.ui.theme.screens


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de Usuario", color = Color.Black)

        // Campo de Nombre de Usuario
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre campos

        // Campo de Correo Electrónico
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre campos

        // Campo de Contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Mostrar Contraseña"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre campos

        // Botón de Registro
        Button(
            onClick = { registerUser(context, username, email, password, navController) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EE)), // Usamos backgroundColor
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        ) {
            Text(text = "Registrar", color = Color.White)
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