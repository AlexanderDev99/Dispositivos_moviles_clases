package com.example.dispositivos_moviles_clases

import android.R
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dispositivos_moviles_clases.databinding.ActivityPrincipalBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class Principal : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private  lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()
    }

    private  fun initVariables() {
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

        //SPINNER
        var options = listOf("YouTube", "Google", "Facebook", "Apple")
        var myAdapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            // R es una clase estatica que me permite acceder a todos los recursos de mi proyecto
            options)

        binding.spinnerURLs.apply {
            adapter = myAdapter
            onItemSelectedListener = this@Principal
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private  fun initListeners() {
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

        binding.logoutBtn.setOnClickListener {

            //Alert dialog
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle("Cerrar sesion")
                .setMessage("¿Esta usted seguro de cerrar sesion?")
                .setCancelable(true)
                //caso si desea,os cerrar sesion
                .setPositiveButton("Si"){
                        dialog, id ->
                   val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                    // caso no desea cerrar sesion
                .setNegativeButton("No"){
                        dialog, id -> dialog.cancel()
                }
                    // caso si desea cancelar
                .setNeutralButton("Cancelar"){
                        dialog, id -> dialog.dismiss()
                }
                .show()

            //cerramos sesion cambiando de pantalla
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onItemSelected(
        p0: AdapterView<*>?,
        p1: View?,
        p2: Int,
        p3: Long
    ) {
        Toast.makeText(this, "Posicion seleccionada es ${position}", Toast.LENGTH_LONG)
            .show()
    }

    override  fun onNothingSelected(p0: AdapterView<*>?){
        TODO("Not yet implemented")
    }
}