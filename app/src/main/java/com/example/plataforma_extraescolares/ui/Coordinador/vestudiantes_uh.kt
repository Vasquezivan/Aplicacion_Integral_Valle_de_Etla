package com.example.plataforma_extraescolares.ui.Coordinador

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R

class vestudiantes_uh : Fragment(){

        private lateinit var etBuscar: EditText
        private lateinit var tablaEstudiantes: TableLayout

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.vestudiantes_uh, container, false)

            etBuscar = view.findViewById(R.id.etBuscar)
            tablaEstudiantes = view.findViewById(R.id.tablaEstudiantes)

            setupSearch()
            assignListenersToAllRows()

            return view
        }

        private fun setupSearch() {
            etBuscar.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    filterRows(s?.toString() ?: "")
                }
            })
        }

        private fun assignListenersToAllRows() {
            for (i in 1 until tablaEstudiantes.childCount) {
                val child = tablaEstudiantes.getChildAt(i)
                if (child is TableRow) {
                    attachRowListeners(child)
                }
            }
        }

        private fun attachRowListeners(fila: TableRow) {
            val accionesView = fila.getChildAt(4)
            if (accionesView is LinearLayout) {
                for (j in 0 until accionesView.childCount) {
                    val v = accionesView.getChildAt(j)
                    if (v is Button) {
                        val texto = v.text?.toString()?.lowercase() ?: ""
                        when {
                            texto.contains("editar") -> v.setOnClickListener { onEditClicked(fila) }
                            texto.contains("eliminar") -> v.setOnClickListener { onDeleteClicked(fila) }
                        }
                    }
                }
            }
        }

        private fun onEditClicked(fila: TableRow) {
            val nombre = (fila.getChildAt(0) as? TextView)?.text?.toString() ?: ""
            val noControl = (fila.getChildAt(1) as? TextView)?.text?.toString() ?: ""
            val carrera = (fila.getChildAt(2) as? TextView)?.text?.toString() ?: ""
            val semestre = (fila.getChildAt(3) as? TextView)?.text?.toString() ?: ""

            mostrarDialogoEditar(nombre, noControl, carrera, semestre)
        }

        private fun onDeleteClicked(fila: TableRow) {
            AlertDialog.Builder(requireContext())
                .setTitle("Eliminar estudiante")
                .setMessage("¿Deseas eliminar este estudiante?")
                .setPositiveButton("Sí") { _, _ ->
                    tablaEstudiantes.removeView(fila)
                    Toast.makeText(requireContext(), "Estudiante eliminado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        private fun mostrarDialogoEditar(nombre: String, noControl: String, carrera: String, semestre: String) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_editar_estudiante1_coordinador, null)

            val etNombre = dialogView.findViewById<EditText>(R.id.etNombreEstudiante)
            val etNoControl = dialogView.findViewById<EditText>(R.id.etNoControl)
            val etCarrera = dialogView.findViewById<EditText>(R.id.etCarrera)
            val etSemestre = dialogView.findViewById<EditText>(R.id.etSemestre)
            val btnCancelar = dialogView.findViewById<Button>(R.id.btnCancelar)
            val btnAgregar = dialogView.findViewById<Button>(R.id.btnAgregar)

            etNombre.setText(nombre)
            etNoControl.setText(noControl)
            etCarrera.setText(carrera)
            etSemestre.setText(semestre)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            btnCancelar.setOnClickListener { dialog.dismiss() }

            btnAgregar.setOnClickListener {
                Toast.makeText(requireContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun filterRows(query: String) {
            val q = query.trim().lowercase()
            for (i in 1 until tablaEstudiantes.childCount) {
                val child = tablaEstudiantes.getChildAt(i)
                if (child is TableRow) {
                    val nombreTv = child.getChildAt(0) as? TextView
                    val controlTv = child.getChildAt(1) as? TextView
                    val nombre = nombreTv?.text?.toString()?.lowercase() ?: ""
                    val control = controlTv?.text?.toString()?.lowercase() ?: ""
                    child.visibility = if (q.isEmpty() || nombre.contains(q) || control.contains(q)) View.VISIBLE else View.GONE
                }
            }
        }
    }

