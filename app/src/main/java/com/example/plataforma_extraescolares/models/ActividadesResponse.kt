package com.example.plataforma_extraescolares.models

import com.google.gson.annotations.SerializedName

data class ActividadesResponse(
    @SerializedName("actividades")
    val actividades: List<Actividad>
)
