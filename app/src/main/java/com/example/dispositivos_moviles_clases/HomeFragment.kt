package com.example.dispositivos_moviles_clases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragmento que representa la pantalla de Inicio.
 */
class HomeFragment : Fragment() {
    
    // Este método se encarga de crear y devolver la jerarquía de vistas asociada al fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // "Inflamos" el layout XML correspondiente para este fragmento
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}