package com.example.plataforma_extraescolares

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plataforma_extraescolares.models.LoginRequest
import com.example.plataforma_extraescolares.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val authService = ApiClient.authService
            val loginRequest = LoginRequest(usuario, password)

            authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            TokenStorage.saveToken(loginResponse.token)
                            Toast.makeText(this@LoginActivity, "Acceso correcto", Toast.LENGTH_SHORT).show()

                            val user = loginResponse.user
                            var roleRecognized = true

                            // Redirigir según el rol y la unidad académica del usuario
                            if (user.rol.equals("Administrador", ignoreCase = true)) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else if (user.rol.equals("Coordinador", ignoreCase = true)) {
                                val unidadAcademica = user.unidadAcademica?.trim() ?: ""
                                when {
                                    unidadAcademica.equals("Unidad académica Tlahuitoltepec", ignoreCase = true) -> {
                                        val intent = Intent(this@LoginActivity, Santa_Maria_Tlahuitoltep::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    unidadAcademica.equals("CIDERS unión Hidalgo", ignoreCase = true) -> {
                                        val intent = Intent(this@LoginActivity, Union_Hidalgo::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    unidadAcademica.equals("Unidad Demetrio Vallejo en el Espinal", ignoreCase = true) -> {
                                        val intent = Intent(this@LoginActivity, Dimitrio_Vallejo::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    unidadAcademica.equals("Valle de Etla", ignoreCase = true) -> {
                                        val intent = Intent(this@LoginActivity, CoordinadorActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else -> {
                                        roleRecognized = false
                                        Toast.makeText(this@LoginActivity, "Unidad académica no reconocida: '${user.unidadAcademica}'", Toast.LENGTH_LONG).show()
                                    }
                                }
                            } else {
                                roleRecognized = false
                                Toast.makeText(this@LoginActivity, "Rol no reconocido: '${user.rol}'", Toast.LENGTH_LONG).show()
                            }

                        } else {
                            Toast.makeText(this@LoginActivity, "Respuesta de inicio de sesión nula", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Manejar error de inicio de sesión (ej. credenciales incorrectas)
                        Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Manejar error de red
                    Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}