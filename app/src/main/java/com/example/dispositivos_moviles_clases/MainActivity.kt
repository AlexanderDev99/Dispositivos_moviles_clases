package com.example.dispositivos_moviles_clases

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dispositivos_moviles_clases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //variables peresosas
    //con inflar el layout dejamos de usar este metodo
//    lateinit var button: Button
//    lateinit var userName : TextView
//    lateinit var userEmail : TextView
//    lateinit var userPassword : TextView
    private lateinit var binding: ActivityMainBinding

    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflamos el layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variables de inicializacion
        initVariables()
        initListeners()

        //variable boton de registro
        //inflando dejamos de crear las variables
        //var button = findViewById<Button>(R.id.RegisterButton)

        //variable entradas del formulario
        //inflando dejamos de crear las variables
//        userName = findViewById<TextView>(R.id.textUserName)
//        userEmail = findViewById<TextView>(R.id.textEmail)
//        userPassword = findViewById<TextView>(R.id.textPassword)
    }

    private fun initListeners() {
        binding.LoginButton.setOnClickListener {

            //definimos las condiciones
//            var msg = ""
//            if(binding.textUserName.toString() == "admin" && binding.textPassword.toString() == "admin") {
//
//                var intent = Intent(this, Principal::class.java)
//                startActivity(intent)
//            }
//            else
//                //toast elemento visual no modificable
//            Toast.makeText(
//                this, //contexto -> this, este contexto tiene que ver con ciclo de vida
//                msg, // lo que vamos a mostrar
//                Toast.LENGTH_LONG) // tiempo de duracion en la pantalla
//                .show()

            val email = binding.textEmail.editText?.text.toString().trim()
            val password = binding.textPassword.editText?.text.toString().trim()

            if (email == "admin@admin.com" && password == "admin") {
                val intent = Intent(this, Principal::class.java)
                intent.putExtra("user_email", email)
                startActivity(intent)
                finish() // Opcional: cerrar la pantalla de login para que no se regrese con "atrás"
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }

        binding.textViewNoAccount.setOnClickListener {
            // Aquí se navegaría a la actividad de Registro cuando esté implementada
            Toast.makeText(this, "Navegar a Registro", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initVariables() {

        counter = 1;
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}