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
import com.example.plataforma_extraescolares.databinding.FragmentValleDeEtlaBinding
import com.example.plataforma_extraescolares.models.ActividadesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Valle_de_Etla : Fragment() {

    private var _binding: FragmentValleDeEtlaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ActividadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentValleDeEtlaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchActividades()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewActividadesValleDeEtla.layoutManager = LinearLayoutManager(requireContext())
        adapter = ActividadAdapter(emptyList()) { actividad ->
            val intent = Intent(requireContext(), Actividades_Valle_de_Etla::class.java)
            intent.putExtra("ID_ACTIVIDAD", actividad.idActividad)
            intent.putExtra("NOMBRE_ACTIVIDAD", actividad.nombre)
            intent.putExtra("DESCRIPCION_ACTIVIDAD", actividad.descripcion)
            startActivity(intent)
        }
        binding.recyclerViewActividadesValleDeEtla.adapter = adapter
    }

    private fun fetchActividades() {
        val apiService = ApiClient.instance.create(ApiService::class.java)
        apiService.getActividadesPorUnidad("Valle de Etla").enqueue(object : Callback<ActividadesResponse> {
            override fun onResponse(call: Call<ActividadesResponse>, response: Response<ActividadesResponse>) {
                if (response.isSuccessful) {
                    val actividades = response.body()?.actividades ?: emptyList()
                    if (actividades.isNotEmpty()) {
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
