package com.example.what2eat.data.di

import android.util.Log
import com.example.what2eat.data.local.RecipeDetailsDao
import com.example.what2eat.domain.repository.FireRepository
import com.example.what2eat.data.remote.Constants.BASE_URL
import com.example.what2eat.data.remote.FoodRecipesApi
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val TAG = "API-LOGS"

    @Provides
    @Singleton
    fun provideFireRecipeRepository()
    = FireRepository(queryRecipe = FirebaseFirestore.getInstance().collection("recipes"))

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message: String ->
            Log.d(TAG, message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", "809924b3fc1442dcae6b18bd05d86520").build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodRecipesApi(okHttpClient: OkHttpClient): FoodRecipesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipesApi::class.java)
    }
}