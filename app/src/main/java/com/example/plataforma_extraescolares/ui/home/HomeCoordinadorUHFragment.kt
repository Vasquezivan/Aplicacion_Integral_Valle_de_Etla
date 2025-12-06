package com.example.plataforma_extraescolares.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plataforma_extraescolares.R
import android.widget.Button

class HomeCoordinadorUHFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_coordinador_uh, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerEstudiantes = view.findViewById<Button>(R.id.btnVerEstudiantes)

        // NAVEGACIÓN CORRECTA
        btnVerEstudiantes.setOnClickListener {
            findNavController().navigate(R.id.nav_vestudiantes_uh)
        }
    }
}
