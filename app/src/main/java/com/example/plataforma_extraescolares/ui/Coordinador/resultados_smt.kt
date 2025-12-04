package com.example.plataforma_extraescolares.ui.Coordinador

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R

class resultados_smt : Fragment() {





        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.resultados_smt, container, false)

            //val btnVerResultados: Button = view.findViewById(R.id.btnVerResultados)
            val btnImprimir: Button = view.findViewById(R.id.btnImprimir)

            // btnVerResultados.setOnClickListener {
            // Acción si se requiere
            //  }

            btnImprimir.setOnClickListener {
                mostrarImagen()
            }

            return view
        }

        private fun mostrarImagen() {
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.dialog_imagen, null)
            val imageView: ImageView = dialogView.findViewById(R.id.imgResultado)
            imageView.setImageResource(R.drawable.resultados1)
            builder.setView(dialogView)
            builder.setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        }
    }


