package com.example.plataforma_extraescolares.models

import com.google.gson.annotations.SerializedName

data class Estudiante(
    @SerializedName("id_alumno") // Mapeo para el ID correcto de la BD
    val id: Int,
    val nombre: String,
    @SerializedName("numero_control")
    val noControl: String?,
    val carrera: String,
    val semestre: String
)
