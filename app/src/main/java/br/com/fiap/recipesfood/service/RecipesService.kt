package br.com.fiap.recipesfood.service

import br.com.fiap.recipesfood.model.Ingredient
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
    suspend fun saveRecipe(@Body recipeRequest: RecipeRequest): RecipeRequest

    @POST("recipes/{recipeId}/ingredients")
    suspend fun saveRecipeIngredients(
        @Path("recipeId") recipeId: Int,
        @Body ingredients: List<Ingredient>
        ): List<Ingredient>

}