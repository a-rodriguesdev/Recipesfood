package br.com.fiap.recipesfood.factory

import br.com.fiap.recipesfood.service.CategoryService
import br.com.fiap.recipesfood.service.RecipesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "http://10.0.2.2:8080/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCategoryService(): CategoryService{
        return retrofit.create(CategoryService::class.java)
    }

    fun getRecipeService(): RecipesService {
        return retrofit.create(RecipesService::class.java)
    }

}