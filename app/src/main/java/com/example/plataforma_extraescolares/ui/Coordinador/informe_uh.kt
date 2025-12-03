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

class informe_uh  : Fragment(){


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_informe, container, false)

            val btnVerInforme: Button = view.findViewById(R.id.btnVerInforme)
            btnVerInforme.setOnClickListener {
                mostrarImagenInforme()
            }

            return view
        }

        private fun mostrarImagenInforme() {
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.dialog_imagen, null)
            val imageView: ImageView = dialogView.findViewById(R.id.imgInforme)
            imageView.setImageResource(R.drawable.informe)
            builder.setView(dialogView)
            builder.setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        }
    }

