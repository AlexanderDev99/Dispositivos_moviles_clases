package com.example.dispositivos_moviles_clases.application.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.example.dispositivos_moviles_clases.logic.usercases.SaveUserUC
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {

    private val _counterUI = MutableLiveData<Int>(0)
    val counterUI: LiveData<Int> get() = _counterUI

    val userRemote get() = _userRemote
    private var _userRemote = MutableLiveData<UserDtoRemote?>()

    private var counterJob: Job? = null

    fun Contador() {
        // Cancelar el trabajo anterior si existe para evitar que se solapen múltiples contadores
        counterJob?.cancel()
        
        counterJob = viewModelScope.launch {
            var counter = 0
            _counterUI.value = counter // Reiniciar a 0
            for (i in 1..10) {
                delay(1000)
                counter++
                _counterUI.value = counter
            }
        }
    }

    fun guardarUsuario( user: UserDtoRemote, db: FirebaseFirestore, saveUserUC: SaveUserUC) {
        // 3. Guardamos en Firebase
            viewModelScope.launch(Dispatchers.Main) {
                val usnew = saveUserUC.saveUser(user, db)
                val usr = usnew.getOrNull()
                if(usnew.getOrNull() != null){
                    _userRemote.value = usr
                }else
                    (UserDtoRemote("","",""))


            }
    }


}
