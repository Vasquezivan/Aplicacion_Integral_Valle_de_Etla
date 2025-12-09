package com.example.plataforma_extraescolares.ui.Administrador

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
import com.example.plataforma_extraescolares.models.Usuario

class UsuarioAdapter(private val usuarios: List<Usuario>) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.bind(usuario)
    }

    override fun getItemCount(): Int = usuarios.size

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombre: TextView = itemView.findViewById(R.id.nombreUsuario)
        private val unidad: TextView = itemView.findViewById(R.id.unidadUsuario)
        private val password: TextView = itemView.findViewById(R.id.passwordUsuario)
        private val rol: TextView = itemView.findViewById(R.id.rolUsuario)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkUsuario)

        fun bind(usuario: Usuario) {
            nombre.text = usuario.nombre
            unidad.text = usuario.unidad_academica
            password.text = usuario.contrasena_texto
            rol.text = usuario.rol

            checkBox.isChecked = adapterPosition == selectedPosition

            // Cambiar el color del CheckBox si está seleccionado
            if (checkBox.isChecked) {
                checkBox.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.azul_marino))
            } else {
                checkBox.buttonTintList = ColorStateList.valueOf(Color.BLACK) // O el color por defecto que prefieras
            }

            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener

                val previousPosition = selectedPosition
                selectedPosition = if (adapterPosition == selectedPosition) -1 else adapterPosition

                // Notificar el cambio del item previo para desmarcarlo
                if (previousPosition != -1) {
                    notifyItemChanged(previousPosition)
                }
                // Notificar el cambio del nuevo item para marcarlo
                if (selectedPosition != -1) {
                    notifyItemChanged(selectedPosition)
                }
            }
            
            checkBox.setOnClickListener {
                itemView.performClick()
            }
        }
    }

    fun getSelectedUsuario(): Usuario? {
        val index = selectedPosition
        return if (index != -1 && index < usuarios.size) {
            usuarios[index]
        } else {
            null
        }
    }
}