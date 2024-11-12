package com.example.mesadeayuda2.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cases")
data class Case(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val descripcion: String,
    val fecha: String,
    val estado: String,
    val nombreUsuario: String // Asumo que cada caso tiene un nombre de usuario relacionado
)
