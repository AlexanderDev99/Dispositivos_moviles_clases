package com.example.dispositivos_moviles_clases.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.dispositivos_moviles_clases.R
import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Fragmento que representa la pantalla de Inicio.
 */
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

        initListeners()
        initVariables()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initListeners(){
        binding.btnRegresar.setOnClickListener {

            //creamos el usuario
            val user = UserDtoRemote(
                id = "",
                name = binding.nameUser.text.toString(),
                lastname = binding.lastnameUser.text.toString()
            )

            lifecycleScope.launch(Dispatchers.Main) {

                val usnew = withContext(Dispatchers.IO) {
                    saveUser(user)
                }

                if(usnew.getOrNull() != null){
                    Snackbar.make(binding.nameUser, "Usuario guardado", Snackbar.LENGTH_LONG)
                        .show()
                }
                }


//            // Add a new document with a generated ID
//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w("TAG", "Error adding document", e)
//                }
        }
    }

    private suspend fun  saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        var resp = db.collection("users")
            .add(user)
            .await().runCatching{
                user
            }
        return resp
    }
    private fun initVariables(){
        db = Firebase.firestore
    }
}