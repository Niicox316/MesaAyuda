package com.example.mesadeayuda2.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var isAuthenticated by mutableStateOf(false)
    var errorMessage: String? by mutableStateOf(null)
    var userRole: String? by mutableStateOf(null)

    fun authenticateUser(email: String, password: String) {
        // Limpia el mensaje de error cada vez que se intenta autenticar
        errorMessage = null

        // Primero, verifica las credenciales en la colección "users"
        db.collection("users")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password) // Asegúrate de que este campo sea el correcto
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    // Si no hay documentos, significa que las credenciales son inválidas
                    errorMessage = "Credenciales inválidas."
                    isAuthenticated = false
                } else {
                    for (document in documents) {
                        userRole = document.getString("role")
                        // Verifica si el rol es "Usuario"
                        if (userRole == "Usuario") {
                            isAuthenticated = true
                        } else {
                            errorMessage = "Acceso denegado: Rol no autorizado."
                            isAuthenticated = false
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                // Manejo de error en caso de fallo en la consulta
                errorMessage = "Error al autenticar: ${e.message}"
                isAuthenticated = false
            }
    }
}
