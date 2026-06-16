package com.example.dispositivos_moviles_clases.application.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivos_moviles_clases.databinding.ActivityMainBinding

/**
 * Actividad inicial que funciona como pantalla de Login.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Configuración de los eventos de click
        initListeners()
    }

    private fun initListeners() {
        // Listener para el botón de inicio de sesión
        binding.LoginButton.setOnClickListener {
            // Obtenemos el texto de los campos de entrada
            val email = binding.textEmail.editText?.text.toString().trim()
            val password = binding.textPassword.editText?.text.toString().trim()

            // Validación simple de credenciales (quemadas para ejemplo)
            if (email == "admin" && password == "admin") {
                // Si es correcto, navegamos a MenuActivity (la pantalla con Fragments)
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("user_email", email)
                startActivity(intent)
                finish() // Cerramos la pantalla de login para que no se pueda volver atrás con el botón físico
            } else {
                // Si es incorrecto, mostramos un mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }

        // Listener para el texto de "No tienes cuenta"
        binding.textViewNoAccount.setOnClickListener {
            // Por ahora solo muestra un mensaje informativo
            Toast.makeText(this, "Navegar a Registro", Toast.LENGTH_SHORT).show()
        }
    }
}