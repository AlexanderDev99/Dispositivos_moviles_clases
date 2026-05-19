package com.example.dispositivos_moviles_clases

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivos_moviles_clases.adapters.CustomAdapter
import com.example.dispositivos_moviles_clases.databinding.ActivityPrincipalBinding
import com.example.dispositivos_moviles_clases.dto.Empresas
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class Principal : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private  lateinit var binding: ActivityPrincipalBinding
    var adapterRecyclerView = CustomAdapter(
        {getName(it)},
        {deleteEmpresas(it)})

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

        //RECYCLER VIEW

        var optionsEmpresas = listOf<Empresas>(
            Empresas("Youtube",
                "https://www.nintendo.com/eu/media/images/10_share_images/games_15/nintendo_switch_download_software_1/H2x1_NSwitchDS_YouTube_image1600w.jpg",
                "https://www.youtube.com/"),
            Empresas("Google",
                "https://yt3.googleusercontent.com/bAseQlKvNmjdLQrvYWm_q3QDp8C8YKyYI-nYJewgOkPi0JU1_3X9oFgjrEdzkOlXzLGFxFbnsw=s900-c-k-c0x00ffffff-no-rj",
                "https://google.com.ec"),
            Empresas("Facebook",
                "https://cdn-1.webcatalog.io/catalog/facebook/facebook-social-preview.png?v=1776040668231",
                "https://www.facebook.com/"),
            Empresas("Apple",
                "https://storage.googleapis.com/webdesignledger.pub.network/WDL/maxresdefault.jpg",
                "https://www.apple.com/")
        )

        binding.RvUrls.adapter = adapterRecyclerView
        //NOTA: //en kotlin los parametros normales van dentro del parentesis de las variables
        // pero si es una funcion el parametro, va por fuera

// FORMA 1
//        binding.RvUrls.layoutManager = LinearLayoutManager(
//            this,
//            RecyclerView.HORIZONTAL,
//            false //va de 1 al 5, si quiero alreves usamos true
//        )

        //FORMA 2
        binding.RvUrls.layoutManager = GridLayoutManager(
            this, 2
        )

        //Carga denuevo si hay cambios
        adapterRecyclerView.lista = optionsEmpresas as MutableList<Empresas>
        adapterRecyclerView.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //impresion del nombre de la empresa
    fun getName(emp: Empresas){
        //obteniendo el nombre de la empresa para mostrar en un snackbar
//        Snackbar.make(binding.RvUrls,
//            Emp.name,
//            Snackbar.LENGTH_LONG)
//            .show()
        //abriendo la url de la empresa
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.setData(Uri.parse(emp.url))
//        startActivity(intent)
        //buscando la empresa en el navegador
        val intent2 = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, emp.name)
        startActivity(intent2)
    }

    fun deleteEmpresas(emp: Empresas){
        var newEmpresas = adapterRecyclerView.lista.minus(emp)
        adapterRecyclerView.lista = newEmpresas as MutableList<Empresas>
        adapterRecyclerView.notifyDataSetChanged()
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "Posicion seleccionada es ${position}", Toast.LENGTH_LONG)
            .show()
    }

    override  fun onNothingSelected(p0: AdapterView<*>?){
        TODO("Not yet implemented")
    }
}