package com.example.plataforma_extraescolares.ui.Administrador

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.plataforma_extraescolares.R
import com.google.android.material.button.MaterialButton

class GUsuariosFragment : Fragment() {

    // ===== 1. DECLARACIONES DE VISTAS =====
    private lateinit var botonEditar: MaterialButton

    private lateinit var checkUsuario1: CheckBox
    private lateinit var checkUsuario2: CheckBox

    private lateinit var nombreUsuario1: TextView
    private lateinit var unidadUsuario1: TextView
    private lateinit var contactoUsuario1: TextView
    private lateinit var passwordUsuario1: TextView
    private lateinit var rolUsuario1: TextView

    private lateinit var nombreUsuario2: TextView
    private lateinit var unidadUsuario2: TextView
    private lateinit var contactoUsuario2: TextView
    private lateinit var passwordUsuario2: TextView
    private lateinit var rolUsuario2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Parámetros si los hay
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_g_usuarios, container, false)

        // ===== 2. INICIALIZACIÓN DE VISTAS =====
        botonEditar = view.findViewById(R.id.botonEditar)

        checkUsuario1 = view.findViewById(R.id.checkEstudiante1)
        checkUsuario2 = view.findViewById(R.id.checkEstudiante2)

        nombreUsuario1 = view.findViewById(R.id.nombreEstudiante1)
        unidadUsuario1 = view.findViewById(R.id.controlEstudiante1)
        contactoUsuario1 = view.findViewById(R.id.contactoEstudiante1)
        passwordUsuario1 = view.findViewById(R.id.passwordEstudiante1)
        rolUsuario1 = view.findViewById(R.id.rolEstudiante1)

        nombreUsuario2 = view.findViewById(R.id.nombreEstudiante2)
        unidadUsuario2 = view.findViewById(R.id.controlEstudiante2)
        contactoUsuario2 = view.findViewById(R.id.contactoEstudiante2)
        passwordUsuario2 = view.findViewById(R.id.passwordEstudiante2)
        rolUsuario2 = view.findViewById(R.id.rolEstudiante2)

        // 3. Configurar el listener del botón "Editar"
        botonEditar.setOnClickListener {
            if (checkUsuario1.isChecked && checkUsuario2.isChecked) {
                Toast.makeText(requireContext(), "Selecciona solo un usuario para editar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (checkUsuario1.isChecked) {
                val nombre = nombreUsuario1.text.toString()
                val unidad = unidadUsuario1.text.toString()
                val contacto = contactoUsuario1.text.toString()
                val password = passwordUsuario1.text.toString()
                val rol = rolUsuario1.text.toString()
                showEditDialog(nombre, unidad, contacto, password, rol, checkUsuario1)

            } else if (checkUsuario2.isChecked) {
                val nombre = nombreUsuario2.text.toString()
                val unidad = unidadUsuario2.text.toString()
                val contacto = contactoUsuario2.text.toString()
                val password = passwordUsuario2.text.toString()
                val rol = rolUsuario2.text.toString()
                showEditDialog(nombre, unidad, contacto, password, rol, checkUsuario2)

            } else {
                Toast.makeText(requireContext(), "Por favor, selecciona un usuario para editar", Toast.LENGTH_SHORT).show()
            }
        }

        // Lógica para que solo un CheckBox pueda estar seleccionado a la vez
        checkUsuario1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkUsuario2.isChecked = false
            }
        }
        checkUsuario2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkUsuario1.isChecked = false
            }
        }

        return view
    }

    /**
     * Función que crea y muestra el diálogo modal para editar un usuario.
     */
    private fun showEditDialog(nombre: String, unidad: String, contacto: String, password: String, rol: String, checkBoxSeleccionado: CheckBox) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_editar_usuario)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val etNombre = dialog.findViewById<EditText>(R.id.editTextNombre)
        val spinnerUnidad = dialog.findViewById<Spinner>(R.id.spinnerUnidadAcademica)
        val etContacto = dialog.findViewById<EditText>(R.id.editTextContacto)
        val etPassword = dialog.findViewById<EditText>(R.id.editTextContraseña)
        val spinnerRol = dialog.findViewById<Spinner>(R.id.spinnerRol)
        val btnGuardar = dialog.findViewById<MaterialButton>(R.id.botonGuardar)
        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.botonCancelar)

        // Configurar Spinner de Unidad Académica
        val unidades = arrayOf("Seleccionar unidad", "Etla (Valles)", "Unión Hidalgo (Istmo)", "El Espinal (Istmo)", "Santa María Tlahuitoltepec (Mixe)")
        val adapterUnidad = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unidades)
        adapterUnidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnidad.adapter = adapterUnidad

        // Configurar Spinner de Rol
        val roles = arrayOf("Seleccionar rol", "Administrador", "Coordinador")
        val adapterRol = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, roles)
        adapterRol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRol.adapter = adapterRol

        // Cargar datos actuales en los campos
        etNombre.setText(nombre)
        etContacto.setText(contacto)
        etPassword.setText(password)

        // Seleccionar la unidad académica actual en el spinner
        val posicionUnidad = unidades.indexOf(unidad)
        if (posicionUnidad >= 0) {
            spinnerUnidad.setSelection(posicionUnidad)
        } else {
            spinnerUnidad.setSelection(0) // "Seleccionar unidad"
        }

        // Seleccionar el rol actual en el spinner
        val posicionRol = roles.indexOf(rol)
        if (posicionRol >= 0) {
            spinnerRol.setSelection(posicionRol)
        } else {
            spinnerRol.setSelection(0) // "Seleccionar rol"
        }

        // Configurar el toggle de mostrar/ocultar contraseña
        setupPasswordToggle(dialog, etPassword)

        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevaUnidad = spinnerUnidad.selectedItem.toString()
            val nuevoContacto = etContacto.text.toString()
            val nuevaPassword = etPassword.text.toString()
            val nuevoRol = spinnerRol.selectedItem.toString()

            // Validaciones
            if (nuevoNombre.isEmpty()) {
                Toast.makeText(requireContext(), "El nombre es obligatorio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nuevaUnidad == "Seleccionar unidad") {
                Toast.makeText(requireContext(), "Selecciona una unidad académica", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nuevoRol == "Seleccionar rol") {
                Toast.makeText(requireContext(), "Selecciona un rol", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Actualizar la interfaz según el checkbox seleccionado
            if (checkBoxSeleccionado.id == R.id.checkEstudiante1) {
                nombreUsuario1.text = nuevoNombre
                unidadUsuario1.text = nuevaUnidad
                contactoUsuario1.text = nuevoContacto
                passwordUsuario1.text = nuevaPassword // Mostrar contraseña real (como en tu XML)
                rolUsuario1.text = nuevoRol
            } else if (checkBoxSeleccionado.id == R.id.checkEstudiante2) {
                nombreUsuario2.text = nuevoNombre
                unidadUsuario2.text = nuevaUnidad
                contactoUsuario2.text = nuevoContacto
                passwordUsuario2.text = nuevaPassword // Mostrar contraseña real (como en tu XML)
                rolUsuario2.text = nuevoRol
            }

            Toast.makeText(requireContext(), "Datos de '$nuevoNombre' actualizados", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
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

    /**
     * Función para configurar el toggle de mostrar/ocultar contraseña
     */
    private fun setupPasswordToggle(dialog: Dialog, editTextPassword: EditText) {
        val togglePassword = dialog.findViewById<ImageView>(R.id.togglePassword)
        var isPasswordVisible = false

        togglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Mostrar contraseña
                editTextPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePassword.setImageResource(R.drawable.eye_open)
            } else {
                // Ocultar contraseña
                editTextPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePassword.setImageResource(R.drawable.eye_closed)
            }

            // Mover el cursor al final del texto
            editTextPassword.setSelection(editTextPassword.text.length)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance() =
            GUsuariosFragment().apply {
                arguments = Bundle().apply {
                    // Puedes agregar parámetros aquí si los necesitas
                }
            }
    }
}