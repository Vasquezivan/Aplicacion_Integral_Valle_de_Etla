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

        // Estado inicial
        var passwordVisible = false

        // Mostrar/Ocultar contraseña
        btnMostrarPassword.setOnClickListener {
            val start = etPassword.selectionStart
            val end = etPassword.selectionEnd

            if (passwordVisible) {
                etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_closed)
            } else {
                etPassword.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_open)
            }

            passwordVisible = !passwordVisible

            etPassword.setSelection(start, end)
            etPassword.typeface = etPassword.typeface
        }

        // Botón Login
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // VALIDACIÓN DE ADMIN
            if (usuario == "admin" && password == "1234") {
                Toast.makeText(this, "Acceso correcto (Admin)", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                // VALIDACIÓN DE COORDINADOR
            } else if (usuario == "coordinador" && password == "5678") {
                Toast.makeText(this, "Acceso como Coordinador", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, CoordinadorActivity::class.java)
                startActivity(intent)
                finish()

                // CUALQUIER OTRO USUARIO ES INCORRECTO
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
