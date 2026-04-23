package com.example.dispositivos_moviles_clases

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflamos el layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable boton de registro
        //inflando dejamos de crear las variables
        //var button = findViewById<Button>(R.id.RegisterButton)

        //variable entradas del formulario
        //inflando dejamos de crear las variables
//        userName = findViewById<TextView>(R.id.textUserName)
//        userEmail = findViewById<TextView>(R.id.textEmail)
//        userPassword = findViewById<TextView>(R.id.textPassword)


        binding.RegisterButton.setOnClickListener {

            //definimos las condiciones
            var msg = ""
            if(binding.textUserName.text.toString() == "admin" && binding.textPassword.text.toString() == "12345")
                msg = "registro con exito!"
            else
                msg = "registro fallido!"


            //toast elemento visual no modificable
            Toast.makeText(
                this, //contexto -> this, este contexto tiene que ver con ciclo de vida
                "Hola Mundo!", // lo que vamos a mostrar
                Toast.LENGTH_LONG) // tiempo de duracion en la pantalla
                .show()
        }
    }
}