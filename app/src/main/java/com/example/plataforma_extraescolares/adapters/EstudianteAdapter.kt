package com.example.plataforma_extraescolares.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.models.Estudiante

class EstudianteAdapter(private var estudiantes: List<Estudiante>) : RecyclerView.Adapter<EstudianteAdapter.ViewHolder>() {

    private var selectedPosition = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkEstudiante: CheckBox = view.findViewById(R.id.checkEstudiante)
        val nombreEstudiante: TextView = view.findViewById(R.id.nombreEstudiante)
        val controlEstudiante: TextView = view.findViewById(R.id.controlEstudiante)
        val carreraEstudiante: TextView = view.findViewById(R.id.carreraEstudiante)
        val semestreEstudiante: TextView = view.findViewById(R.id.semestreEstudiante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudiante, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = estudiantes[position]
        holder.nombreEstudiante.text = estudiante.nombre
        holder.controlEstudiante.text = if (estudiante.noControl.isNullOrBlank()) "-" else estudiante.noControl
        holder.carreraEstudiante.text = estudiante.carrera
        holder.semestreEstudiante.text = estudiante.semestre

        holder.checkEstudiante.isChecked = selectedPosition == position

        // Cambiar el color del CheckBox si está seleccionado
        if (holder.checkEstudiante.isChecked) {
            holder.checkEstudiante.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.azul_marino))
        } else {
            holder.checkEstudiante.buttonTintList = ColorStateList.valueOf(Color.BLACK) // O el color por defecto que prefieras
        }

        holder.itemView.setOnClickListener {
            val previousSelectedPosition = selectedPosition
            selectedPosition = if (selectedPosition == position) -1 else position

            // Notificar cambios para la selección anterior y la nueva
            if (previousSelectedPosition != -1) {
                notifyItemChanged(previousSelectedPosition)
            }
            notifyItemChanged(selectedPosition)
        }

        holder.checkEstudiante.setOnClickListener {
            holder.itemView.performClick() // Simular clic en el item
        }
    }

    override fun getItemCount() = estudiantes.size

    fun getSelectedItems(): List<Estudiante> {
        return if (selectedPosition != -1) {
            listOf(estudiantes[selectedPosition])
        } else {
            emptyList()
        }
    }

    fun clearSelection() {
        val previousSelectedPosition = selectedPosition
        selectedPosition = -1
        if (previousSelectedPosition != -1) {
            notifyItemChanged(previousSelectedPosition)
        }
    }

    fun updateData(newEstudiantes: List<Estudiante>){
        estudiantes = newEstudiantes
        notifyDataSetChanged()
    }
}
