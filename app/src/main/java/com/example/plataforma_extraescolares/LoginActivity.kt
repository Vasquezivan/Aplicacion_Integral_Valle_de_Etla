package com.example.plataforma_extraescolares

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

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
