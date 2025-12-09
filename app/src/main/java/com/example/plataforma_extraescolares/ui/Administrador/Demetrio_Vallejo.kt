package com.example.plataforma_extraescolares.ui.Administrador

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataforma_extraescolares.ApiClient
import com.example.plataforma_extraescolares.ApiService
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.adapters.ActividadAdapter
import com.example.plataforma_extraescolares.models.ActividadesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Demetrio_Vallejo : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActividadAdapter
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_demetrio__vallejo, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewActividadesDemetrio)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ActividadAdapter(emptyList()) { actividad ->
            val intent = Intent(requireContext(), Actividades_Demetrio_Vallejo::class.java)
            // CORRECCIÓN: Usar el nombre de campo correcto 'idActividad'
            intent.putExtra("ID_ACTIVIDAD", actividad.idActividad)
            intent.putExtra("NOMBRE_ACTIVIDAD", actividad.nombre)
            intent.putExtra("DESCRIPCION_ACTIVIDAD", actividad.descripcion)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        apiService = ApiClient.instance.create(ApiService::class.java)

        fetchActividades()

        return view
    }

    private fun fetchActividades() {
        apiService.getActividadesPorUnidad("Demetrio Vallejo Martínez").enqueue(object : Callback<ActividadesResponse> {
            override fun onResponse(call: Call<ActividadesResponse>, response: Response<ActividadesResponse>) {
                if (response.isSuccessful) {
                    val actividades = response.body()?.actividades ?: emptyList()
                    adapter.updateData(actividades)
                } else {
                    Toast.makeText(requireContext(), "Error al obtener las actividades: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ActividadesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
