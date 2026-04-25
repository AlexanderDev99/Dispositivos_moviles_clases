package com.example.dispositivos_moviles_clases

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

    lateinit var binding: ActivityMainBinding

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

    fun initListeners() {

        binding.RegisterButton.setOnClickListener {

            //definimos las condiciones
            var msg = ""
            if(binding.textUserName.toString() == "admin" && binding.textPassword.toString() == "admin") {

                var intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }
            else
                //toast elemento visual no modificable
            Toast.makeText(
                this, //contexto -> this, este contexto tiene que ver con ciclo de vida
                msg, // lo que vamos a mostrar
                Toast.LENGTH_LONG) // tiempo de duracion en la pantalla
                .show()
        }
    }

    private fun initVariables() {

        counter = 1;
    }


}