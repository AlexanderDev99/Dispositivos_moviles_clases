package com.example.dispositivos_moviles_clases

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dispositivos_moviles_clases.databinding.ActivityPrincipalBinding
import com.google.android.material.snackbar.Snackbar

class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()
    }

    private fun initVariables() {
        //let evita valores nulos con
        // ? decimos que si existe tomalo y si no lo haces nada
        intent.extras.let {
            var saludo = it?.getString("xx1")
            Snackbar.make(
                binding.urlText,
                saludo.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun initListeners() {
        binding.urlBtn.setOnClickListener {
            val url = binding.urlText.text.toString()


            //CREACION DE LA URI


          /*
            //creamos el action view
            val i = Intent(Intent.ACTION_VIEW)
            //transformamos la url a una uri
            i.setData(Uri.parse(url))
            startActivity(i) //inciamos la activity
*/


            //llamada a un mapa
            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.setData(gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }
    }


}