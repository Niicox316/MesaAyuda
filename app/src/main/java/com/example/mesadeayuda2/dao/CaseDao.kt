package com.example.mesadeayuda2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mesadeayuda2.Entidades.Case
import kotlinx.coroutines.flow.Flow

@Dao
interface CaseDao {

    @Query("SELECT * FROM cases")
    fun getAllCases(): Flow<List<Case>>  // Esto devuelve los casos como un Flow

    // Función para actualizar el estado de un caso
    @Update
    suspend fun updateCase(case: Case)

    // Función para eliminar un caso
    @Delete
    suspend fun deleteCase(case: Case)
}
