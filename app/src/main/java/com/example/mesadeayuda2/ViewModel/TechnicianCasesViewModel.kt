package com.example.mesadeayuda2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mesadeayuda2.data.local.CaseDao
import com.example.mesadeayuda2.Entidades.Case
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TechnicianCasesViewModel(private val caseDao: CaseDao) : ViewModel() {

    // Variables para el estado de autenticación y el mensaje de error
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Implementa la lógica de autenticación aquí
    fun authenticateTechnician(username: String, password: String) {
        viewModelScope.launch {
            // Aquí va la lógica para autenticar al técnico (puede ser una llamada a una API o validación local)
            if (username == "admin" && password == "password") {
                _isAuthenticated.value = true
                _errorMessage.value = null
            } else {
                _isAuthenticated.value = false
                _errorMessage.value = "Credenciales incorrectas"
            }
        }
    }

    // Variable para almacenar los casos
    val cases = caseDao.getAllCases()

    // Función para cargar casos desde la base de datos
    fun loadCases() {
        // Lógica para cargar casos
    }

    // Función para actualizar el estado de un caso
    fun updateCaseState(case: Case) {
        // Cambiar el estado del caso según la lógica
        val updatedCase = case.copy(
            estado = when (case.estado) {
                "Abierto" -> "Pendiente"
                "Pendiente" -> "Resuelto"
                else -> "Abierto"
            }
        )

        // Actualizar el caso en la base de datos
        viewModelScope.launch {
            caseDao.updateCase(updatedCase)
        }
    }
}
