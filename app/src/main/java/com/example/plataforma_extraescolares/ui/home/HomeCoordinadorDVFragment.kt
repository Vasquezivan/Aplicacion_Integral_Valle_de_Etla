package com.example.plataforma_extraescolares.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.databinding.FragmentHomeCoordinadorDvBinding
class HomeCoordinadorDVFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_coordinador_dv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // BOTÓN DEL XML
        val btnVerEstudiantes = view.findViewById<Button>(R.id.btnVerEstudiantes)

        btnVerEstudiantes.setOnClickListener {
            // ABRIR OTRO FRAGMENT
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_coordinador_dv, VerEstudiantesFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
