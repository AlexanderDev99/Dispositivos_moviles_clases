package com.example.dispositivos_moviles_clases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dispositivos_moviles_clases.databinding.ActivityMenuBinding

/**
 * Actividad principal del menú que contiene el Navigation Rail vertical y el contenedor de fragmentos.
 */
class MenuActivity : AppCompatActivity() {

    // Uso de ViewBinding para acceder a los componentes del layout de forma segura
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inflamos el layout usando ViewBinding
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    /**
     * Configura los listeners de los componentes de la interfaz.
     */
    private fun initListeners() {
        // Obtenemos el NavHostFragment que contiene el NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // Vinculamos el NavigationRailView con el NavController
        // Esto gestiona automáticamente el cambio de fragmentos basado en los IDs del menú y el NavGraph
        binding.navigationRail.setupWithNavController(navController)
    }
}