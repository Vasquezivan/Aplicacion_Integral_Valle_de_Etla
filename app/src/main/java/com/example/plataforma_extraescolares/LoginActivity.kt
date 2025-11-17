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

            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ===============================
            //      ACCESO ADMINISTRADOR
            // ===============================
            if (usuario == "admin" && password == "1234") {
                Toast.makeText(this, "Acceso como Administrador", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, BienvenidaAdminActivity::class.java)
                startActivity(intent)
                finish()
            }

            // ===============================
            //       ACCESO COORDINADOR
            // ===============================
            else if (usuario == "coordinador" && password == "5678") {
                Toast.makeText(this, "Acceso como Coordinador", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, BienvenidaCoordinadorActivity::class.java)
                startActivity(intent)
                finish()
            }

            // ===============================
            //        DATOS INCORRECTOS
            // ===============================
            else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
