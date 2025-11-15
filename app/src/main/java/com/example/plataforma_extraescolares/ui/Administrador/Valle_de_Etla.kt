package com.example.plataforma_extraescolares.ui.Administrador

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plataforma_extraescolares.R
import com.google.android.material.button.MaterialButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Valle_de_Etla.newInstance] factory method to
 * create an instance of this fragment.
 */
class Valle_de_Etla : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Cargar el diseño XML del fragmento correcto
        val view = inflater.inflate(R.layout.fragment_valle_de__etla, container, false)

        // 2. Referencia al botón desde el XML. El ID es el mismo que en el otro layout.
        val btnVisualizar = view.findViewById<MaterialButton>(R.id.btn_visualizar_estudiantes)

        // 3. Evento de clic en el botón
        btnVisualizar.setOnClickListener {
            // Crear un Intent para abrir la Activity de Valle de Etla
            // NOTA: Asegúrate de que la clase "Actividades_Valle_de_Etla" existe.
            val intent = Intent(requireContext(), Actividades_Valle_de_Etla::class.java)

            // Enviar datos extra a la nueva Activity
            intent.putExtra("actividad", "Banda de Guerra")
            intent.putExtra("sede", "Valle de Etla")

            // Iniciar la nueva Activity
            startActivity(intent)
        }

        // 4. Devolver la vista
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Valle_de_Etla().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
