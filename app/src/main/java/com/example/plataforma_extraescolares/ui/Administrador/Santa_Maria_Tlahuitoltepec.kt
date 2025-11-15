package com.example.plataforma_extraescolares.ui.Administrador

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
 * Use the [Santa_Maria_Tlahuitoltepec.newInstance] factory method to
 * create an instance of this fragment.
 */
class Santa_Maria_Tlahuitoltepec : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_santa__maria__tlahuitoltepec, container, false)

        // Referencia al botón "Visualizar" desde el layout del fragmento
        val btnVisualizar = view.findViewById<MaterialButton>(R.id.btn_visualizar_estudiantes)

        // Configurar el evento de clic para el botón
        btnVisualizar.setOnClickListener {
            // Crear un Intent para abrir la Activity que mostrará los estudiantes de Danza
            // NOTA: Asegúrate de crear una Activity llamada "Actividades_Tlahuitoltepec" o cambia el nombre aquí
            val intent = Intent(requireContext(), Actividades_Santa_Maria_Tlahuitoltepec::class.java)

            // Opcional: puedes enviar datos a la nueva Activity si lo necesitas.
            // Por ejemplo, para indicar qué actividad se seleccionó.
            intent.putExtra("actividad", "Danza")
            intent.putExtra("sede", "Santa María Tlahuitoltepec")

            // Iniciar la nueva Activity
            startActivity(intent)
        }

        // Devolver la vista configurada para que se muestre en la pantalla
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Santa_Maria_Tlahuitoltepec.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Santa_Maria_Tlahuitoltepec().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
