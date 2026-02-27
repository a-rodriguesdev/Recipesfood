package br.com.fiap.recipesfood.service

import br.com.fiap.recipesfood.model.Recipe
import br.com.fiap.recipesfood.model.RecipeRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RecipesService {

   // http://localhost:8080/api/recipes/categories/1


    @GET("recipes/categories/{categoryId}")
    fun getRecipesByCategory(@Path("categoryId") id: Int): Call<List<Recipe>>

    @GET("recipes/recents")
    fun getLatestRecipes(): Call<List<Recipe>>


    @POST("recipes")
    fun saveRecipe(@Body recipeRequest: RecipeRequest): Call<RecipeRequest>

}