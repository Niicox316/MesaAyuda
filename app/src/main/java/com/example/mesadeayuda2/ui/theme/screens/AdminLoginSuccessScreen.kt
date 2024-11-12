package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mesadeayuda2.ViewModel.UserViewModel

@Composable
fun AdminLoginSuccessScreen() {
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users

    // Cargar los usuarios al iniciar la pantalla
    LaunchedEffect(Unit) {
        userViewModel.loadUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp), // Ajusta el valor para agregar más espacio superior
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Lista de Usuarios Registrados:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Mostrar la lista de usuarios
        if (users.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    UserItem(user.name, user.email, user.id, userViewModel)
                }
            }
        } else {
            Text(
                text = "No hay usuarios registrados.",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


@Composable
fun UserItem(name: String, email: String, userId: String, userViewModel: UserViewModel) {
    var isSelected by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isSelected = !isSelected }
            .background(if (isSelected) Color.Cyan else Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Correo: $email", style = MaterialTheme.typography.bodyMedium)

            // Mostrar botones solo si la tarjeta está seleccionada
            if (isSelected) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Lógica para asignar el rol de "Usuario" al usuario
                            userViewModel.assignRole(userId, "Usuario")
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Asignar como Usuario",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Usuario", modifier = Modifier.padding(start = 4.dp))
                    }

                    Button(
                        onClick = {
                            // Lógica para asignar el rol de "Técnico" al usuario
                            userViewModel.assignRole(userId, "Técnico")
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Asignar como Técnico",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Técnico", modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }
}