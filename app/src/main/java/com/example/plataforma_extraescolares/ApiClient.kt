package com.example.plataforma_extraescolares

import com.example.plataforma_extraescolares.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.137.103:8000/api/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // El TokenInterceptor se encargará de añadir Authorization si hay token
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(TokenInterceptor()) // interceptor personalizado
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        instance.create(ApiService::class.java)
    }

    val authService: AuthService by lazy {
        instance.create(AuthService::class.java)
    }
}
