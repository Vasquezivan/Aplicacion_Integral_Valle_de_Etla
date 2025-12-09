package com.example.plataforma_extraescolares.models

data class Usuario(
    val id_usuario: Int,
    val id_semestre: Int,
    val nombre: String,
    val contrasena: String?,
    val contrasena_texto: String,
    val rol: String,
    val unidad_academica: String
)