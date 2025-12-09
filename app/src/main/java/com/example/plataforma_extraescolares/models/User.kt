package com.example.plataforma_extraescolares.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id_usuario")
    val idUsuario: Int,
    @SerializedName("nombre")
    val nombre: String,
    val email: String?,
    @SerializedName("rol")
    val rol: String,
    @SerializedName("unidad_academica")
    val unidadAcademica: String?
)