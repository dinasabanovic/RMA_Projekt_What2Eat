package com.example.what2eat.domain.repository

import com.example.what2eat.data.DataOrException
import com.example.what2eat.model.MRecipe
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryRecipe: Query
) {
    suspend fun getAllRecipesFromDatabase(): DataOrException<List<MRecipe>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MRecipe>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data = queryRecipe.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MRecipe::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException
    }
}