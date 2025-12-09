package com.example.plataforma_extraescolares.models

import com.google.gson.annotations.SerializedName

data class Semestre(
    @SerializedName("id_semestre")
    val id: Int,
    val nombre: String,
    @SerializedName("estatus")
    val activo: Boolean,
    val fecha_inicio: String,
    val fecha_fin: String
)
