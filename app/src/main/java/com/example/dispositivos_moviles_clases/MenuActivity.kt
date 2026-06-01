package com.example.dispositivos_moviles_clases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        // Cargamos el fragmento inicial (HomeFragment) solo la primera vez que se crea la actividad
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Configuramos el listener para detectar clicks en los elementos del Navigation Rail
        binding.navigationRail.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mn_home -> {
                    // Si se pulsa Inicio, reemplazamos el contenido por HomeFragment
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.mn_samples -> {
                    // Si se pulsa Samples, reemplazamos el contenido por SamplesFragment
                    replaceFragment(SamplesFragment())
                    true
                }
                R.id.mn_explorar -> {
                    // Si se pulsa Explorar, reemplazamos el contenido por ExploreFragment
                    replaceFragment(ExploreFragment())
                    true
                }
                R.id.mn_biblio -> {
                    // Si se pulsa Biblioteca, reemplazamos el contenido por LibraryFragment
                    replaceFragment(LibraryFragment())
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Función auxiliar para realizar la transacción de reemplazo de fragmentos.
     * @param fragment El nuevo fragmento que se desea mostrar.
     */
    private fun replaceFragment(fragment: Fragment) {
        // Usamos supportFragmentManager para gestionar el cambio de fragmentos
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Reemplaza el contenido del FrameLayout
            .commit() // Aplica los cambios
    }
}