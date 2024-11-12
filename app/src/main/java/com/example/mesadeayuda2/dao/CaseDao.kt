package com.example.mesadeayuda2.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mesadeayuda2.Entidades.Case

@Dao
interface CaseDao {
    @Query("SELECT * FROM cases")
    suspend fun getAllCases(): List<Case>
}
