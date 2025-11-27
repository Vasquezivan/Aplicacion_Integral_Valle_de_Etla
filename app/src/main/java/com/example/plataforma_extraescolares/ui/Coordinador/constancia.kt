package com.example.plataforma_extraescolares.ui.Coordinador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R

class constancia : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.constancia, container, false)

        val btnEditar = view.findViewById<Button>(R.id.btnEditar)
        val btnEvaluar = view.findViewById<Button>(R.id.btnEvaluar)

        // Botón para abrir diálogo de edición
        btnEditar.setOnClickListener {
            mostrarDialogoEditarEstudiante()
        }

        // Botón para abrir fragment de formulario
        btnEvaluar.setOnClickListener {
            abrirFormulario()
        }

        return view
    }

    private fun abrirFormulario() {
        // Abrir fragment formulario dentro del mismo NavHostFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_coordinador, formulario())
            .addToBackStack("formulario")
            .commit()
    }

    private fun mostrarDialogoEditarEstudiante() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_editar_estudiante, null)

        val etNombre = dialogView.findViewById<EditText>(R.id.etNombreEstudiante)
        val etNoControl = dialogView.findViewById<EditText>(R.id.etNoControl)
        val etCarrera = dialogView.findViewById<EditText>(R.id.etCarrera)
        val etSemestre = dialogView.findViewById<EditText>(R.id.etSemestre)
        val btnCancelar = dialogView.findViewById<Button>(R.id.btnCancelar)
        val btnGuardar = dialogView.findViewById<Button>(R.id.btnGuardar)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        btnCancelar.setOnClickListener { dialog.dismiss() }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val noControl = etNoControl.text.toString().trim()
            val carrera = etCarrera.text.toString().trim()
            val semestre = etSemestre.text.toString().trim()

            if (nombre.isEmpty() || noControl.isEmpty() || carrera.isEmpty() || semestre.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Datos guardados:\n$nombre, $noControl, $carrera, $semestre", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
