package com.example.plataforma_extraescolares.ui.Administrador

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plataforma_extraescolares.ApiClient
import com.example.plataforma_extraescolares.ApiService
import com.example.plataforma_extraescolares.R
import com.example.plataforma_extraescolares.adapters.EstudianteAdapter
import com.example.plataforma_extraescolares.models.Estudiante
import com.example.plataforma_extraescolares.models.EstudiantesResponse
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Actividades_Valle_de_Etla : AppCompatActivity() {

    private lateinit var botonEditar: MaterialButton
    private lateinit var recyclerViewEstudiantes: RecyclerView
    private lateinit var adapter: EstudianteAdapter
    private lateinit var apiService: ApiService
    private var idActividad: Int = -1

    private lateinit var tituloActividad: TextView
    private lateinit var subtituloActividad: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_valle_de_etla)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir datos del Intent
        idActividad = intent.getIntExtra("ID_ACTIVIDAD", -1)
        val nombreActividad = intent.getStringExtra("NOMBRE_ACTIVIDAD")
        val descripcionActividad = intent.getStringExtra("DESCRIPCION_ACTIVIDAD")

        // Inicializar vistas
        tituloActividad = findViewById(R.id.tituloActividad)
        subtituloActividad = findViewById(R.id.subtituloActividad)
        botonEditar = findViewById(R.id.botonEditar)
        recyclerViewEstudiantes = findViewById(R.id.recyclerViewEstudiantes)

        // Asignar datos a las vistas
        tituloActividad.text = nombreActividad
        subtituloActividad.text = descripcionActividad

        // Configurar RecyclerView
        recyclerViewEstudiantes.layoutManager = LinearLayoutManager(this)
        adapter = EstudianteAdapter(emptyList())
        recyclerViewEstudiantes.adapter = adapter

        // Configurar API y obtener estudiantes
        apiService = ApiClient.instance.create(ApiService::class.java)
        if (idActividad != -1) {
            fetchEstudiantes(idActividad)
        }

        botonEditar.setOnClickListener {
            val selectedItems = adapter.getSelectedItems()
            if (selectedItems.size == 1) {
                val estudiante = selectedItems[0]
                showEditDialog(estudiante)
            } else if (selectedItems.size > 1) {
                Toast.makeText(this, "Selecciona solo un estudiante para editar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, selecciona un estudiante para editar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchEstudiantes(idActividad: Int) {
        apiService.getEstudiantesPorActividad(idActividad).enqueue(object : Callback<EstudiantesResponse> {
            override fun onResponse(call: Call<EstudiantesResponse>, response: Response<EstudiantesResponse>) {
                if (response.isSuccessful) {
                    val estudiantes = response.body()?.estudiantes ?: emptyList()
                    adapter.updateData(estudiantes)
                    if (estudiantes.isEmpty()) {
                        Toast.makeText(this@Actividades_Valle_de_Etla, "No hay estudiantes inscritos.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Actividades_Valle_de_Etla, "Error al obtener los estudiantes: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EstudiantesResponse>, t: Throwable) {
                Toast.makeText(this@Actividades_Valle_de_Etla, "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showEditDialog(estudiante: Estudiante) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_editar_estudiante)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val etNombre = dialog.findViewById<EditText>(R.id.editTextNombre)
        val etNoControl = dialog.findViewById<EditText>(R.id.editTextNoControl)
        val etCarrera = dialog.findViewById<EditText>(R.id.editTextCarrera)
        val etSemestre = dialog.findViewById<EditText>(R.id.editTextSemestre)
        val btnGuardar = dialog.findViewById<MaterialButton>(R.id.botonGuardar)
        val btnCancelar = dialog.findViewById<MaterialButton>(R.id.botonCancelar)

        etNombre.setText(estudiante.nombre)
        etNoControl.setText(estudiante.noControl)
        etCarrera.setText(estudiante.carrera)
        etSemestre.setText(estudiante.semestre.toString())

        btnGuardar.setOnClickListener {
            val estudianteActualizado = estudiante.copy(
                nombre = etNombre.text.toString(),
                noControl = etNoControl.text.toString(),
                carrera = etCarrera.text.toString(),
                semestre = etSemestre.text.toString()
            )

            apiService.updateEstudiante(estudiante.id, estudianteActualizado).enqueue(object : Callback<Estudiante> {
                override fun onResponse(call: Call<Estudiante>, response: Response<Estudiante>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@Actividades_Valle_de_Etla, "Estudiante actualizado correctamente", Toast.LENGTH_SHORT).show()
                        fetchEstudiantes(idActividad)
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this@Actividades_Valle_de_Etla, "Error al actualizar el estudiante: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Estudiante>, t: Throwable) {
                    Toast.makeText(this@Actividades_Valle_de_Etla, "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
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
}
