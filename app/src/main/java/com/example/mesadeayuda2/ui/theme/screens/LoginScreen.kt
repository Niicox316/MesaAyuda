package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mesadeayuda2.ViewModel.LoginViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = viewModel()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val isAuthenticated by remember { derivedStateOf { loginViewModel.isAuthenticated } }
    val errorMessage by remember { derivedStateOf { loginViewModel.errorMessage } }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Función para validar los campos
    fun validateFields(): Boolean {
        var isValid = true

        // Validación del nombre de usuario
        if (username.isBlank()) {
            usernameError = "El nombre de usuario es obligatorio"
            isValid = false
        } else {
            usernameError = ""
        }

        // Validación del correo electrónico
        if (email.isBlank()) {
            emailError = "El correo electrónico es obligatorio"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Correo electrónico inválido"
            isValid = false
        } else {
            emailError = ""
        }

        // Validación de la contraseña
        if (password.isBlank()) {
            passwordError = "La contraseña es obligatoria"
            isValid = false
        } else {
            passwordError = ""
        }

        return isValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de nombre de usuario
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),
            isError = usernameError.isNotEmpty()
        )
        if (usernameError.isNotEmpty()) {
            Text(text = usernameError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de correo electrónico
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty()
        )
        if (emailError.isNotEmpty()) {
            Text(text = emailError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            isError = passwordError.isNotEmpty()
        )
        if (passwordError.isNotEmpty()) {
            Text(text = passwordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                if (validateFields()) {
                    loginViewModel.authenticateUser(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el mensaje de error solo si hay un error
        if (errorMessage != null) {
            Text(
                text = "Error: $errorMessage",
                color = Color.Red
            )
        }

        // Navegación y mensaje de éxito
        if (isAuthenticated) {
            LaunchedEffect(Unit) {
                // Mostrar el mensaje de éxito
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Ingreso exitoso")
                }

                // Navegar a la pantalla de éxito, pasando el correo electrónico
                navController.navigate("login_success/${username}") {
                    popUpTo("login") { inclusive = true } // Limpia la pila de navegación si es necesario
                }
            }
        }

        // Host para mostrar el Snackbar
        SnackbarHost(hostState = snackbarHostState)
    }
}
