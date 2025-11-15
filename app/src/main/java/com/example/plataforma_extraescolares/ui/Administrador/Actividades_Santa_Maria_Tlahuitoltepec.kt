package com.example.plataforma_extraescolares.ui.Administrador

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.plataforma_extraescolares.R
import com.google.android.material.button.MaterialButton

class Actividades_Santa_Maria_Tlahuitoltepec : AppCompatActivity() {

    // ===== 1. DECLARACIONES DE VISTAS =====
    private lateinit var botonEditar: MaterialButton
    // private lateinit var botonEliminar: MaterialButton // Mantenido comentado

    private lateinit var checkEstudiante1: CheckBox
    private lateinit var checkEstudiante2: CheckBox

    private lateinit var nombreEstudiante1: TextView
    private lateinit var controlEstudiante1: TextView
    private lateinit var carreraEstudiante1: TextView
    private lateinit var semestreEstudiante1: TextView
    private lateinit var nombreEstudiante2: TextView
    private lateinit var controlEstudiante2: TextView
    private lateinit var carreraEstudiante2: TextView
    private lateinit var semestreEstudiante2: TextView

    private lateinit var divider1: View
    private lateinit var divider2: View


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- LÍNEA CORREGIDA ---
        enableEdgeToEdge()

        // Establece el layout específico para esta Activity
        setContentView(R.layout.activity_actividades_santa_maria_tlahuitoltepec)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ===== 2. INICIALIZACIÓN DE VISTAS =====
        botonEditar = findViewById(R.id.botonEditar)
        // botonEliminar = findViewById(R.id.botonEliminar) // Mantenido comentado

        checkEstudiante1 = findViewById(R.id.checkEstudiante1)
        checkEstudiante2 = findViewById(R.id.checkEstudiante2)

        nombreEstudiante1 = findViewById(R.id.nombreEstudiante1)
        controlEstudiante1 = findViewById(R.id.controlEstudiante1)
        carreraEstudiante1 = findViewById(R.id.carreraEstudiante1)
        semestreEstudiante1 = findViewById(R.id.semestreEstudiante1)
        nombreEstudiante2 = findViewById(R.id.nombreEstudiante2)
        controlEstudiante2 = findViewById(R.id.controlEstudiante2)
        carreraEstudiante2 = findViewById(R.id.carreraEstudiante2)
        semestreEstudiante2 = findViewById(R.id.semestreEstudiante2)

        divider1 = findViewById(R.id.divider1)
        divider2 = findViewById(R.id.divider2)

        // 3. Configurar el listener del botón "Editar".
        botonEditar.setOnClickListener {
            if (checkEstudiante1.isChecked && checkEstudiante2.isChecked) {
                Toast.makeText(this, "Selecciona solo un estudiante para editar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (checkEstudiante1.isChecked) {
                val nombre = nombreEstudiante1.text.toString()
                val control = controlEstudiante1.text.toString()
                val carrera = carreraEstudiante1.text.toString()
                val semestre = semestreEstudiante1.text.toString()
                showEditDialog(nombre, control, carrera, semestre, checkEstudiante1)

            } else if (checkEstudiante2.isChecked) {
                val nombre = nombreEstudiante2.text.toString()
                val control = controlEstudiante2.text.toString()
                val carrera = carreraEstudiante2.text.toString()
                val semestre = semestreEstudiante2.text.toString()
                showEditDialog(nombre, control, carrera, semestre, checkEstudiante2)

            } else {
                Toast.makeText(this, "Por favor, selecciona un estudiante para editar", Toast.LENGTH_SHORT).show()
            }
        }

        // Lógica para que solo un CheckBox pueda estar seleccionado a la vez
        checkEstudiante1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkEstudiante2.isChecked = false
            }
        }
        checkEstudiante2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkEstudiante1.isChecked = false
            }
        }

        // ===== CÓDIGO DE ELIMINACIÓN (COMENTADO) =====
        /*
        botonEliminar.setOnClickListener {
            if (checkEstudiante1.isChecked && checkEstudiante2.isChecked) {
                Toast.makeText(this, "Selecciona solo un estudiante para eliminar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nombre: String
            when {
                checkEstudiante1.isChecked -> {
                    nombre = nombreEstudiante1.text.toString()
                    mostrarDialogoDeConfirmacion(1, nombre)
                }
                checkEstudiante2.isChecked -> {
                    nombre = nombreEstudiante2.text.toString()
                    mostrarDialogoDeConfirmacion(2, nombre)
                }
                else -> {
                    Toast.makeText(this, "Por favor, selecciona un estudiante para eliminar", Toast.LENGTH_SHORT).show()
                }
            }
        }
        */
    } // Fin de onCreate

    // ===== FUNCIONES DE ELIMINACIÓN (COMENTADAS) =====
    /*
    private fun mostrarDialogoDeConfirmacion(numeroEstudiante: Int, nombre: String) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Eliminación")
            .setMessage("¿Estás seguro de que quieres eliminar a '$nombre'?")
            .setPositiveButton("Sí, Eliminar") { _, _ ->
                eliminarEstudiante(numeroEstudiante)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun eliminarEstudiante(numeroEstudiante: Int) {
        val vistasParaOcultar: List<View> = if (numeroEstudiante == 1) {
            listOf(checkEstudiante1, nombreEstudiante1, controlEstudiante1, carreraEstudiante1, semestreEstudiante1, divider1)
        } else {
            listOf(checkEstudiante2, nombreEstudiante2, controlEstudiante2, carreraEstudiante2, semestreEstudiante2, divider2)
        }

        vistasParaOcultar.forEach { vista ->
            vista.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    vista.visibility = View.GONE
                }
        }
        Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_SHORT).show()
    }
    */

    /**
     * Función que crea y muestra el diálogo modal para editar un estudiante.
     */
    private fun showEditDialog(nombre: String, noControl: String, carrera: String, semestre: String, checkBoxSeleccionado: CheckBox) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        // Usa el mismo layout de diálogo que la otra activity
        dialog.setContentView(R.layout.dialog_editar_estudiante)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val etNombre = dialog.findViewById<EditText>(R.id.editTextNombre)
        val etNoControl = dialog.findViewById<EditText>(R.id.editTextNoControl)
        val etCarrera = dialog.findViewById<EditText>(R.id.editTextCarrera)
        val etSemestre = dialog.findViewById<EditText>(R.id.editTextSemestre)
        val btnGuardar = dialog.findViewById<MaterialButton>(R.id.botonGuardar)
        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.botonCancelar)

        etNombre.setText(nombre)
        etNoControl.setText(noControl)
        etCarrera.setText(carrera)
        etSemestre.setText(semestre)

        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevoControl = etNoControl.text.toString()
            val nuevaCarrera = etCarrera.text.toString()
            val nuevoSemestre = etSemestre.text.toString()

            if (checkBoxSeleccionado.id == R.id.checkEstudiante1) {
                nombreEstudiante1.text = nuevoNombre
                controlEstudiante1.text = nuevoControl
                carreraEstudiante1.text = nuevaCarrera
                semestreEstudiante1.text = nuevoSemestre
            } else if (checkBoxSeleccionado.id == R.id.checkEstudiante2) {
                nombreEstudiante2.text = nuevoNombre
                controlEstudiante2.text = nuevoControl
                carreraEstudiante2.text = nuevaCarrera
                semestreEstudiante2.text = nuevoSemestre
            }

            Toast.makeText(this, "Datos de '$nuevoNombre' actualizados", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setLayout(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.show()
    }
}
