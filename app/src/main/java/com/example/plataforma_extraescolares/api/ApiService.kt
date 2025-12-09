package com.example.plataforma_extraescolares.api

import com.example.plataforma_extraescolares.models.Semestre
import com.example.plataforma_extraescolares.models.Usuario
import com.example.plataforma_extraescolares.models.UsuariosPorSemestreResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("usuarios")
    suspend fun getUsuarios(): Response<List<Usuario>>

    @GET("semestre-activo")
    suspend fun getSemestreActivo(): Response<Semestre>

    @GET("usuarios/semestre/{id}")
    suspend fun getUsuariosPorSemestre(@Path("id") semestreId: Int): Response<UsuariosPorSemestreResponse>

    @PUT("usuarios/{id}")
    suspend fun updateUsuario(@Path("id") id: Int, @Body usuario: Usuario): Response<Usuario>
}
