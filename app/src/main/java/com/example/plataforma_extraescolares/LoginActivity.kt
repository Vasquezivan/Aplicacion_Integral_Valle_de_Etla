package com.example.plataforma_extraescolares

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.InputType

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a elementos del layout
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnMostrarPassword = findViewById<ImageView>(R.id.btnMostrarPassword)

        // Estado inicial de la contraseña oculta
        var passwordVisible = false

        // Mostrar / Ocultar contraseña
        btnMostrarPassword.setOnClickListener {
            val cursorPos = etPassword.selectionStart

            if (passwordVisible) {
                // Ocultar contraseña
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_closed)
            } else {
                // Mostrar contraseña
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btnMostrarPassword.setImageResource(R.drawable.eye_open)
            }

            passwordVisible = !passwordVisible
            etPassword.setSelection(cursorPos)
        }

        // BOTÓN LOGIN
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // ACCESO ADMIN
            if (usuario == "admin" && password == "1234") {
                Toast.makeText(this, "Acceso como Administrador", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                // ACCESO COORDINADOR
            } else if (usuario == "coordinador" && password == "5678") {
                Toast.makeText(this, "Acceso como Coordinador", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CoordinadorActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
