package br.com.fiap.recipesfood.service

import br.com.fiap.recipesfood.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryService {

    @GET("categories")
    fun getAllCategories(): Call<List<Category>>

    @GET("categories/{categoryId}")
    fun getCategoryById(@Path("categoryId") categoryId: Int): Call<Category>

}