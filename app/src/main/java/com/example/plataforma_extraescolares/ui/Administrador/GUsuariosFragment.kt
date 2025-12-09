package com.example.plataforma_extraescolares.ui.Administrador

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataforma_extraescolares.ApiClient
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.models.Usuario
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class GUsuariosFragment : Fragment() {

    private lateinit var botonEditar: MaterialButton
    private lateinit var recyclerUsuarios: RecyclerView
    private lateinit var adapter: UsuarioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_g_usuarios, container, false)

        botonEditar = view.findViewById(R.id.botonEditar)
        recyclerUsuarios = view.findViewById(R.id.recyclerUsuarios)
        recyclerUsuarios.layoutManager = LinearLayoutManager(requireContext())

        fetchUsuariosDelSemestreActivo()

        botonEditar.setOnClickListener {
            if (::adapter.isInitialized) {
                val selectedUsuario = adapter.getSelectedUsuario()
                if (selectedUsuario != null) {
                    showEditDialog(selectedUsuario)
                } else {
                    Toast.makeText(requireContext(), "Por favor, selecciona un usuario para editar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Cargando usuarios...", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun fetchUsuariosDelSemestreActivo() {
        lifecycleScope.launch {
            try {
                // 1. Obtener el semestre activo
                val semestreResponse = ApiClient.apiService.getSemestreActivo()
                if (semestreResponse.isSuccessful && semestreResponse.body() != null) {
                    val semestreActivo = semestreResponse.body()!!

                    // 2. Obtener usuarios de ese semestre
                    val usuariosResponse = ApiClient.apiService.getUsuariosPorSemestre(semestreActivo.id)
                    if (usuariosResponse.isSuccessful && usuariosResponse.body() != null) {
                        val usuarios = usuariosResponse.body()!!.usuarios
                        adapter = UsuarioAdapter(usuarios)
                        recyclerUsuarios.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(), "Error al obtener los usuarios del semestre", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener el semestre activo", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showEditDialog(usuario: Usuario) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_editar_usuario)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val etNombre = dialog.findViewById<EditText>(R.id.editTextNombre)
        val spinnerUnidad = dialog.findViewById<Spinner>(R.id.spinnerUnidadAcademica)
        val etPassword = dialog.findViewById<EditText>(R.id.editTextContraseña)
        val spinnerRol = dialog.findViewById<Spinner>(R.id.spinnerRol)
        val btnGuardar = dialog.findViewById<MaterialButton>(R.id.botonGuardar)
        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.botonCancelar)

        // Configurar Spinners...
        val unidades = arrayOf("Seleccionar unidad", "Etla (Valles)", "Unión Hidalgo (Istmo)", "El Espinal (Istmo)", "Santa María Tlahuitoltepec (Mixe)")
        val adapterUnidad = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unidades)
        spinnerUnidad.adapter = adapterUnidad

        val roles = arrayOf("Seleccionar rol", "Administrador", "Coordinador")
        val adapterRol = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, roles)
        spinnerRol.adapter = adapterRol

        // Cargar datos actuales
        etNombre.setText(usuario.nombre)
        etPassword.setText(usuario.contrasena_texto)
        spinnerUnidad.setSelection(unidades.indexOf(usuario.unidad_academica).coerceAtLeast(0))
        spinnerRol.setSelection(roles.indexOf(usuario.rol).coerceAtLeast(0))

        setupPasswordToggle(dialog, etPassword)

        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevaUnidad = spinnerUnidad.selectedItem.toString()
            val nuevaPassword = etPassword.text.toString()
            val nuevoRol = spinnerRol.selectedItem.toString()

            // Validaciones...

            val usuarioActualizado = usuario.copy(
                nombre = nuevoNombre,
                unidad_academica = nuevaUnidad,
                contrasena_texto = nuevaPassword,
                rol = nuevoRol
            )

            lifecycleScope.launch {
                try {
                    val response = ApiClient.apiService.updateUsuario(usuario.id_usuario, usuarioActualizado)
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show()
                        fetchUsuariosDelSemestreActivo()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(requireContext(), "Error al actualizar el usuario: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.show()
    }

    private fun setupPasswordToggle(dialog: Dialog, editTextPassword: EditText) {
        val togglePassword = dialog.findViewById<ImageView>(R.id.togglePassword)
        var isPasswordVisible = false

        togglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePassword.setImageResource(R.drawable.eye_open)
            } else {
                editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePassword.setImageResource(R.drawable.eye_closed)
            }

            editTextPassword.setSelection(editTextPassword.text.length)
        }
    }
}