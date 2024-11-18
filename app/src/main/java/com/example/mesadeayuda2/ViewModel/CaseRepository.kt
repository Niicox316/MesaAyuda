package com.example.mesadeayuda2.data

import com.example.mesadeayuda2.Entidades.Case
import com.example.mesadeayuda2.data.local.CaseDao
import kotlinx.coroutines.flow.Flow

class CaseRepository(private val caseDao: CaseDao) {

    // Función para obtener todos los casos
    suspend fun getAllCases(): Flow<List<Case>> {
        return caseDao.getAllCases()  // Asumimos que tienes un método en el DAO para obtener todos los casos
    }
}
