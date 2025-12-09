package com.example.plataforma_extraescolares.models

data class UsuariosPorSemestreResponse(
    val semestre: Semestre,
    val usuarios: List<Usuario>
)
