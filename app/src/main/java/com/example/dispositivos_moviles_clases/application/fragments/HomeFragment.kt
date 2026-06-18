package com.example.dispositivos_moviles_clases.application.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dispositivos_moviles_clases.application.viewmodels.HomeViewModel
import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.databinding.FragmentHomeBinding
import com.example.dispositivos_moviles_clases.logic.usercases.GetAllUsersUC
import com.example.dispositivos_moviles_clases.logic.usercases.SaveUserUC
import com.example.dispositivos_moviles_clases.repositories.connections.UserRepository
import com.example.dispositivos_moviles_clases.repositories.connections.remote.UserRemoteImpl
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Fragmento que representa la pantalla de Inicio.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var db = Firebase.firestore

    private val homeVM by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 1. Inicializar binding correctamente
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 2. Llamar a los inits aquí, después de que la vista se ha creado
        initVariables()
        initListeners()
        initObservers()


        // EL OBSERVER VA AQUÍ, FUERA DEL CLICK
        homeVM.counterUI.observe(viewLifecycleOwner) { valor ->
            binding.contadorTxt.text = valor.toString()
        }
    }

    private fun initListeners() {
        binding.btnRegresar.setOnClickListener {

            // 1. Iniciamos el contador en el ViewModel
            homeVM.Contador()

            // 2. Preparamos los datos del usuario
            val user = UserDtoRemote(
                id = "111",
                name = binding.nameUser.text.toString(),
                lastname = binding.lastnameUser.text.toString()
            )

//Guardar Usuarios
//            lifecycleScope.launch (Dispatchers.Main){
//                homeVM.guardarUsuario(
//                    user,
//                    SaveUserUC(
//                        UserRepository(
//                            UserRemoteImpl(db)
//                        )
//                    )
//                )
//            }
            lifecycleScope.launch {
                homeVM.listarUsuarios(
                    GetAllUsersUC(
                        UserRepository(
                            UserRemoteImpl(db)
                        )
                    )
                )
            }
        }
    }


    private fun initObservers() {

        homeVM.userRemote.observe(viewLifecycleOwner) {
            Snackbar.make(
                binding.nameUser,
                it?.name + "Registrado correctamente",
                Snackbar.LENGTH_LONG
            ).show()
        }

        homeVM.listaUsuarios.observe(viewLifecycleOwner) { users ->
           Log.d("TAG", "Listando Usuarios")
            users.forEach {
                Log.d("TAG", it.toString())
            }
        }

    }

    private fun initVariables() {
        db = Firebase.firestore
    }
}
