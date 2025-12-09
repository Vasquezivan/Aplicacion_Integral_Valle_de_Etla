package com.example.plataforma_extraescolares.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.models.Actividad
import com.google.android.material.button.MaterialButton

class ActividadAdapter(
    private var actividades: List<Actividad>,
    private val onItemClick: ((Actividad) -> Unit)? = null
) : RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actividad, parent, false)
        return ActividadViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActividadViewHolder, position: Int) {
        val actividad = actividades[position]
        holder.bind(actividad)
    }

    override fun getItemCount(): Int = actividades.size

    fun updateData(newActividades: List<Actividad>) {
        actividades = newActividades
        notifyDataSetChanged()
    }

    inner class ActividadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreActividad: TextView = itemView.findViewById(R.id.textViewNombreActividad)
        private val btnVisualizar: MaterialButton = itemView.findViewById(R.id.btn_visualizar)

        fun bind(actividad: Actividad) {
            nombreActividad.text = actividad.nombre
            onItemClick?.let { clickListener ->
                btnVisualizar.setOnClickListener { clickListener(actividad) }
                itemView.setOnClickListener { clickListener(actividad) }
            }
        }
    }
}
