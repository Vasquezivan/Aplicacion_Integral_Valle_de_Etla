package com.example.plataforma_extraescolares.ui.Administrador

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R
import com.google.android.material.button.MaterialButton

class Union_Hidalgo : Fragment() {

    // Parámetros opcionales (puedes eliminarlos si no los usas)
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Cargar el diseño XML del fragmento
        val view = inflater.inflate(R.layout.fragment_union__hidalgo, container, false)

        // Referencia al botón desde el XML
        val btnVisualizar = view.findViewById<MaterialButton>(R.id.btn_visualizar_estudiantes)

        // Evento de clic en el botón
        btnVisualizar.setOnClickListener {
            // Crear un Intent para abrir la Activity Actividades_Union_Hidalgo
            val intent = Intent(requireContext(), Actividades_Union_Hidalgo::class.java)

            // Si quieres, puedes enviar datos (por ejemplo, la actividad seleccionada)
            // intent.putExtra("actividad", "Basquetbol")

            // Iniciar la nueva Activity
            startActivity(intent)
        }

        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Union_Hidalgo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
