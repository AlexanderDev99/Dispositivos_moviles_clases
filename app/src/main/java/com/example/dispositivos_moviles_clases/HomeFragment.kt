package com.example.dispositivos_moviles_clases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * Fragmento que representa la pantalla de Inicio.
 */
class HomeFragment : Fragment() {
    
    // Este método se encarga de crear y devolver la jerarquía de vistas asociada al fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<Button>(R.id.btnGoToSamples).setOnClickListener {
            findNavController().navigate(R.id.action_mn_home_to_mn_samples)
        }
    }
}