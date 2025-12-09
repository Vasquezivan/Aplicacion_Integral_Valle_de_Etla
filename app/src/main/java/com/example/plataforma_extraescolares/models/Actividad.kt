package com.example.plataforma_extraescolares.models

import com.google.gson.annotations.SerializedName

data class Actividad(
    @SerializedName("id_actividad")
    val idActividad: Int,

    @SerializedName("nombre_actividad")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String?,

    @SerializedName("horario")
    val horario: String?,

    @SerializedName("unidad_academica")
    val unidadAcademica: String
)