package com.example.dispositivos_moviles_clases

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dispositivos_moviles_clases.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.urlBtn.setOnClickListener {
            val url = binding.urlText.text.toString()


            //CREACION DE LA URI

            //creamos el action view
            val i = Intent(Intent.ACTION_VIEW)
            //transformamos la url a una uri
            i.setData(Uri.parse(url))
            startActivity(i) //inciamos la activity

        }
    }


}