package com.example.plataforma_extraescolares

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnMostrarPassword = findViewById<ImageView>(R.id.btnMostrarPassword)

        // Estado inicial de la contraseña oculta
        var passwordVisible = false

        // Función para mostrar/ocultar contraseña
        btnMostrarPassword.setOnClickListener {
            // Guardar posición del cursor
            val start = etPassword.selectionStart
            val end = etPassword.selectionEnd

            if (passwordVisible) {
                // Ocultar contraseña
                etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_closed)
            } else {
                // Mostrar contraseña
                etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_open)
            }

            passwordVisible = !passwordVisible

            // Restaurar la posición del cursor
            etPassword.setSelection(start, end)

            // Mantener el estilo de letra original
            etPassword.typeface = etPassword.typeface
        }

        // Botón de login
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val password = etPassword.text.toString()

            // Validación simple (puedes conectar a BD después)
            if (usuario == "admin" && password == "1234") {
                Toast.makeText(this, "Acceso correcto", Toast.LENGTH_SHORT).show()

                // Ir al MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // evita que regrese al login con el botón atrás
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
