package com.example.mesadeayuda2.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = ""
)

class UserViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("users")

    // Estado para la lista de usuarios
    val users = mutableStateOf<List<User>>(emptyList())

    // Estado para el mensaje de asignación de rol
    var roleAssignmentMessage = mutableStateOf<String?>(null)

    // Cargar usuarios desde Firestore
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val snapshot = usersCollection.get().await()
                val userList = snapshot.documents.mapNotNull { document ->
                    document.toObject(User::class.java)?.copy(id = document.id)
                }
                users.value = userList
            } catch (e: Exception) {
                // Manejo de errores de carga
                println("Error al cargar usuarios: ${e.message}")
            }
        }
    }

    // Función para asignar un rol a un usuario
    fun assignRole(userId: String, role: String) {
        viewModelScope.launch {
            try {
                // Asignar el rol en Firestore
                usersCollection.document(userId).update("role", role).await()

                // Actualizar el mensaje de asignación
                roleAssignmentMessage.value = "Rol '$role' asignado correctamente."

                // Refrescar la lista de usuarios después de asignar el rol
                loadUsers()
            } catch (e: Exception) {
                // En caso de error, mostrar mensaje de error
                roleAssignmentMessage.value = "Error al asignar rol: ${e.message}"
            }
        }
    }
}
