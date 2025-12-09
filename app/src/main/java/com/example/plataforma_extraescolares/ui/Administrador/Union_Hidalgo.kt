package com.example.plataforma_extraescolares.ui.Administrador

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plataforma_extraescolares.ApiClient
import com.example.plataforma_extraescolares.ApiService
import com.example.plataforma_extraescolares.adapters.ActividadAdapter
import com.example.plataforma_extraescolares.databinding.FragmentUnionHidalgoBinding
import com.example.plataforma_extraescolares.models.ActividadesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Union_Hidalgo : Fragment() {

    private var _binding: FragmentUnionHidalgoBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ActividadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnionHidalgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchActividades()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewActividadesUnionHidalgo.layoutManager = LinearLayoutManager(requireContext())
        // 1. Inicializar el adaptador con la lógica de clic
        adapter = ActividadAdapter(emptyList()) { actividad ->
            val intent = Intent(requireContext(), Actividades_Union_Hidalgo::class.java)
            intent.putExtra("ID_ACTIVIDAD", actividad.idActividad)
            intent.putExtra("NOMBRE_ACTIVIDAD", actividad.nombre)
            intent.putExtra("DESCRIPCION_ACTIVIDAD", actividad.descripcion)
            startActivity(intent)
        }
        // 2. Asignar el adaptador al RecyclerView
        binding.recyclerViewActividadesUnionHidalgo.adapter = adapter
    }

    private fun fetchActividades() {
        val apiService = ApiClient.instance.create(ApiService::class.java)
        apiService.getActividadesPorUnidad("Unión Hidalgo").enqueue(object : Callback<ActividadesResponse> {
            override fun onResponse(call: Call<ActividadesResponse>, response: Response<ActividadesResponse>) {
                if (response.isSuccessful) {
                    val actividades = response.body()?.actividades ?: emptyList()
                    if (actividades.isNotEmpty()) {
                        // 3. Actualizar los datos del adaptador
                        adapter.updateData(actividades)
                    } else {
                        Toast.makeText(requireContext(), "No hay actividades para mostrar.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al cargar las actividades. Código: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ActividadesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
