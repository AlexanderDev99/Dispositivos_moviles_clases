package com.example.dispositivos_moviles_clases.application.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cloudinary.android.MediaManager
import com.example.dispositivos_moviles_clases.application.viewmodels.HomeViewModel
import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.databinding.FragmentHomeBinding
import com.example.dispositivos_moviles_clases.repositories.CloudinaryService
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Fragmento que representa la pantalla de Inicio.
 */
class HomeFragment : Fragment() {

    //variable activity for result
    private val viewGalery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){ uri: Uri ->
        if(uri != null){
            // Guardar en archivo temporal
            val valid = guardarArchivoTemporal(uri)
            if(valid){
                //if(se guardo en el archivo temporal??)
                Toast.makeText(requireContext(), "El archivo esta listo para ser subido", Toast.LENGTH_SHORT)
                    .show()
                subirImagen()
            }else{
                Toast.makeText(requireContext(), "No se pudo guardar el archivo", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

//Funcion para guardar archivo temporal
    private fun guardarArchivoTemporal(uri : Uri): Boolean{
       return try{
            val contentResolver = requireContext().contentResolver
            val temporalFile = File(requireContext().cacheDir, "img_temp.jpg")
            val inputStream = contentResolver.openInputStream(uri) ?: return false
            val outputStream = FileOutputStream(temporalFile)
            inputStream.copyTo(outputStream)

            inputStream.close()
            outputStream.close()

             true
        }catch(ex : Exception){
            Log.d("UCE", ex.message.toString())
             false
        }
    }

    private fun subirImagen() {
        val archivoCache = File(requireContext().cacheDir, "img_temp.jpg")

        CloudinaryService.subirImagenFirmada(archivoCache){esExitoso, resultado ->

            lifecycleScope.launch(Dispatchers.IO){
                val resultText = if(esExitoso){
                    "La imagen se subio correctamente en ${resultado}"
                }else{
                    "Error al subir la imagen: ${resultado}"
                }
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        requireContext(),
                        resultText,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

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
        }

        binding.btnAPI.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                homeVM.getUsersTypi()
            }
        }

        binding.btnSubir.setOnClickListener {
            viewGalery.launch(PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


    private fun initObservers() {

        homeVM.typiUsers.observe(viewLifecycleOwner) { items ->
            items?.forEach { users ->
                Log.d("ITEMS", users.name)
            }
        }

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

        //Cloudinary credenciales
        val config = mapOf(
            "cloud_name" to "do3yophmq",
            "api_key" to "559262965767352",
            "api_secret" to "cEMbyMIJiHGbYLhQdXi9eAIRJdY"
        )
        //Inicializacion Global
        MediaManager.init(requireContext(), config)
    }
}
