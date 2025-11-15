package com.example.plataforma_extraescolares.ui.Administrador

// --- Importaciones necesarias ---
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R
import com.google.android.material.button.MaterialButton

class Demetrio_Vallejo : Fragment() {

    // (La sección de parámetros se puede mantener o eliminar, no afecta la funcionalidad principal)
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
        // 1. Cargar (inflar) el diseño XML del fragmento que creamos anteriormente.
        //    Asegúrate de que el nombre del layout es el correcto.
        val view = inflater.inflate(R.layout.fragment_demetrio__vallejo, container, false)

        // 2. Encontrar el botón "Visualizar" dentro de la vista del fragmento.
        //    El ID 'btn_visualizar_estudiantes' es el mismo en ambos layouts, lo que facilita la réplica.
        val btnVisualizar = view.findViewById<MaterialButton>(R.id.btn_visualizar_estudiantes)

        // 3. Configurar el evento de clic para el botón.
        btnVisualizar.setOnClickListener {
            // Crear un Intent para abrir la Activity que mostrará los estudiantes
            // de "Taller de Música Tradicional".
            // **Punto Clave:** Necesitamos crear una nueva Activity, por ejemplo, "Actividades_Demetrio_Vallejo".
            val intent = Intent(requireContext(), Actividades_Demetrio_Vallejo::class.java)

            // (Opcional) Si en el futuro necesitas enviar datos, puedes hacerlo aquí.
            // intent.putExtra("actividad", "Taller de Música Tradicional")

            // Iniciar la nueva Activity.
            startActivity(intent)
        }

        // 4. Devolver la vista ya configurada para que se muestre en pantalla.
        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Demetrio_Vallejo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
