package com.example.plataforma_extraescolares.ui.Coordinador

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R
import com.google.android.material.snackbar.Snackbar

class formulario : Fragment() {

    private lateinit var btnCompartir: Button
    private lateinit var btnVerConstancia: Button
    private lateinit var imgVolver: ImageView
    private lateinit var rgPregunta1: RadioGroup
    private lateinit var rgPregunta9: RadioGroup
    private lateinit var rgPregunta10: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de vistas
        btnCompartir = view.findViewById(R.id.btnCompartir)
        btnVerConstancia = view.findViewById(R.id.btnVerConstancia)
        imgVolver = view.findViewById(R.id.imgVolver)

        rgPregunta1 = view.findViewById(R.id.rgPregunta1)
        rgPregunta9 = view.findViewById(R.id.rgPregunta9)
        rgPregunta10 = view.findViewById(R.id.rgPregunta10)

        // Botón volver
        imgVolver.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Botones principales
        btnCompartir.setOnClickListener { compartirEvaluacion() }
        btnVerConstancia.setOnClickListener { mostrarImagenConstancia() }

        // Respuestas predeterminadas
        rgPregunta1.check(R.id.rbP1Suficiente)
        rgPregunta9.check(R.id.rbP9Suficiente)
        rgPregunta10.check(R.id.rbP10Suficiente)
    }

    // ==========================================================
    // MOSTRAR IMAGEN DE CONSTANCIA
    // ==========================================================
    private fun mostrarImagenConstancia() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_imagen_constancia, null)

        val imageView = dialogView.findViewById<ImageView>(R.id.imgConstancia)
        imageView.setImageResource(R.drawable.constancia1)

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    // ==========================================================
    // COMPARTIR EVALUACIÓN
    // ==========================================================
    private fun compartirEvaluacion() {
        if (rgPregunta1.checkedRadioButtonId != -1 &&
            rgPregunta9.checkedRadioButtonId != -1 &&
            rgPregunta10.checkedRadioButtonId != -1
        ) {
            val datos = """
                Evaluación:
                Pregunta 1: ${obtenerRespuesta(rgPregunta1)}
                Pregunta 9: ${obtenerRespuesta(rgPregunta9)}
                Pregunta 10: ${obtenerRespuesta(rgPregunta10)}
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, datos)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, "Compartir evaluación"))
        } else {
            view?.let {
                Snackbar.make(it, "Por favor responde todas las preguntas", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // ==========================================================
    // OBTENER RESPUESTA DE RADIOGROUP
    // ==========================================================
    private fun obtenerRespuesta(rg: RadioGroup): String {
        val checkedId = rg.checkedRadioButtonId
        val radio = requireView().findViewById<RadioButton>(checkedId)
        return radio.text.toString()
    }
}
