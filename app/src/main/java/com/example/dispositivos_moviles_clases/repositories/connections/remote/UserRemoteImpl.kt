package com.example.dispositivos_moviles_clases.repositories.connections.remote

import com.example.dispositivos_moviles_clases.data.remote.dto.UserDtoRemote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// pasamos 'db' al constructor primario de la clase
// y luego lo enviamos al constructor de la clase padre (UserRemote).
class UserRemoteImpl(private val db: FirebaseFirestore) : UserRemote {

    override suspend fun getAllUsers(): Result<List<UserDtoRemote>> = runCatching {
        // 1. Inicializa una lista mutable vacía para almacenar los usuarios recuperados.
        var lista = arrayListOf<UserDtoRemote>()

        // 2. Hace la consulta a la colección "users" en Firebase Firestore.
        db.collection("users")
            .get()             // Solicita obtener todos los documentos de la colección.
            .await()           // Pausa la ejecución de forma no bloqueante hasta que Firebase responda (gracias a las Corrutinas).
            .forEach {         // Itera sobre cada uno de los documentos (QueryDocumentSnapshot) retornados.

                // 3. Convierte el documento de Firestore directamente a un objeto de tipo UserDtoRemote.
                // Para que esto funcione, UserDtoRemote debe tener un constructor vacío y propiedades que coincidan con los campos de Firestore.
                lista.add(it.toObject(UserDtoRemote::class.java))
            }

        // 4. Retorna explícitamente la lista final. Al estar dentro de 'runCatching',
        // automáticamente se envolverá en un Result.success(lista).
        // Si ocurre alguna excepción en el bloque, runCatching la capturará y devolverá un Result.failure(exception).
        return@runCatching lista
    }

    override suspend fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote> = runCatching {

        var lista = arrayListOf<UserDtoRemote>()

        db.collection("users")
            .whereEqualTo("name", user.name)
            .get()
            .await()
            //foreach maneja el caso de que exiatan usarios con nombres iguales
            .forEach {
                lista.add(it.toObject(UserDtoRemote::class.java))
            }
        return@runCatching lista.first()
    }

    override suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {

            var resp = db.collection("users")
                .add(user)
                .await().runCatching { user }// Espera a que la tarea de Firebase termine
        return resp
    }
}