package com.example.plataforma_extraescolares

import com.example.plataforma_extraescolares.models.LoginRequest
import com.example.plataforma_extraescolares.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun login(@Body req: LoginRequest): Call<LoginResponse>

    @POST("logout")
    fun logout(): Call<java.util.Map<String, String>> // o un DTO
}