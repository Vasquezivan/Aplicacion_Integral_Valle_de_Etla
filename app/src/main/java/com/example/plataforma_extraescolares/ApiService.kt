package com.example.plataforma_extraescolares

import com.example.plataforma_extraescolares.models.ActividadesResponse
import com.example.plataforma_extraescolares.models.Estudiante
import com.example.plataforma_extraescolares.models.EstudiantesResponse
import com.example.plataforma_extraescolares.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("actividades/{unidad}")
    fun getActividadesPorUnidad(@Path("unidad") unidad: String): Call<ActividadesResponse>

    @GET("actividades/{id}/estudiantes")
    fun getEstudiantesPorActividad(@Path("id") idActividad: Int): Call<EstudiantesResponse>

    @PUT("estudiantes/{id}")
    fun updateEstudiante(@Path("id") id: Int, @Body estudiante: Estudiante): Call<Estudiante>

    @GET("usuarios")
    fun getUsuarios(): Call<List<Usuario>>

    @PUT("usuarios/{id}")
    fun updateUsuario(@Path("id") id: Int, @Body usuario: Usuario): Call<Usuario>
}
