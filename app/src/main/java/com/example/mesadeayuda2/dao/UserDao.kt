package com.example.mesadeayuda2.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserDao(private val db: FirebaseFirestore) {

    // Función para obtener el usuario por su correo electrónico
    suspend fun getUserByEmail(email: String): User? {
        return try {
            // Consultar la base de datos para buscar el usuario con el correo proporcionado
            val querySnapshot = db.collection("users")
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .await()

            // Si se encuentra el usuario, crear y devolver un objeto User
            if (!querySnapshot.isEmpty) {
                val document = querySnapshot.documents[0]
                User(
                    name = document.getString("name") ?: "Usuario",
                    email = document.getString("email") ?: ""
                )
            } else {
                null // Usuario no encontrado
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

// Clase de datos User para representar al usuario
data class User(
    val name: String,
    val email: String
)
