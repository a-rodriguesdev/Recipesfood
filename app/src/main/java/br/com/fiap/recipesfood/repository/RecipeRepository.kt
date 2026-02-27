package br.com.fiap.recipesfood.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.fiap.recipesfood.factory.RetrofitClient
import br.com.fiap.recipesfood.model.Category
import br.com.fiap.recipesfood.model.DifficultLevel
import br.com.fiap.recipesfood.model.Recipe
import br.com.fiap.recipesfood.model.RecipeRequest
import br.com.fiap.recipesfood.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

fun getAllRecipes() = listOf(
    Recipe(
        id = 1,
        category = Category(id = 6000, name = "Desserts"),
        user = User(id = 100, name = "Ana Maria"),
        difficultLevel = DifficultLevel.BEGINNER,
        name = "Carrot Cake",
        description = "Moist, spiced, carrot-filled cake with tangy cream cheese frosting.",
        cookingTime = 60,
        createdAt = LocalDate.now(),
        image = "/uploads/bolo_de_cenoura.jpg"
    ),
    Recipe(
        id = 2,
        category = Category(id = 5000, name = "Vegetables"),
        user = User(id = 200, name = "Pedro Augusto"),
        difficultLevel = DifficultLevel.INTERMEDIATE,
        name = "Hearts of Palm Salad",
        description = "Refreshing heart of palm salad, light, savory, and subtly sweet.",
        cookingTime = 10,
        createdAt = LocalDate.now(),
        image = "/uploads/palmito.jpg"
    ),
    Recipe(
        id = 3,
        category = Category(id = 4000, name = "Bakery"),
        user = User(id = 300, name = "Patricia Oliveira"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Calabrese Bread",
        description = "Spicy sausage and cheese bread: soft, savory, delicious.",
        cookingTime = 10,
        createdAt = LocalDate.now(),
        image = "/uploads/pao_calabresa.jpg"
    ),
    Recipe(
        id = 4,
        category = Category(id = 5000, name = "Vegetables"),
        user = User(id = 400, name = "Mariana Dias"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Vegetable Soup",
        description = "Hearty vegetable soup: warm, nourishing, fresh, wholesome goodness.",
        cookingTime = 45,
        createdAt = LocalDate.now(),
        image = "/uploads/sopa_legumes.jpg"
    ),
    Recipe(
        id = 5,
        category = Category(id = 2000, name = "Beef"),
        user = User(id = 500, name = "Carlos Almeida"),
        difficultLevel = DifficultLevel.ADVANCED,
        name = "Feijoada",
        description = "Rich, smoky, hearty, bean and meat stew.",
        cookingTime = 120,
        createdAt = LocalDate.now(),
        image = "/uploads/feijoada.jpg"
    )
)

@Composable
fun getRecipesByCategory(id: Int): List<Recipe> {
    var recipes by remember {
        mutableStateOf(listOf<Recipe>())
    }

    val callRecipesByCategory = RetrofitClient.getRecipeService().getRecipesByCategory(id)

    callRecipesByCategory.enqueue(object : Callback<List<Recipe>> {
        override fun onResponse(
            p0: Call<List<Recipe>?>,
            response: Response<List<Recipe>?>
        ) {
            recipes = response.body() ?: emptyList()
        }

        override fun onFailure(
            p0: Call<List<Recipe>?>,
            p1: Throwable
        ){
            println("ERRO-----> ${p1.printStackTrace()}")
            println(p1.message)
        }
    })
    return recipes
}


@Composable
fun getLatestRecipes(): List<Recipe>{
    var latestRecipes by remember {
        mutableStateOf(listOf<Recipe>())
    }

    val callLatestRecipes = RetrofitClient.getRecipeService().getLatestRecipes()

    callLatestRecipes.enqueue(object : Callback<List<Recipe>> {
        override fun onResponse(
            p0: Call<List<Recipe>?>,
            response: Response<List<Recipe>?>
        ) {
            latestRecipes = response.body() ?: emptyList()
        }

        override fun onFailure(
            p0: Call<List<Recipe>?>,
            p1: Throwable
        ){
            println(p1.message)
        }
    })
    return latestRecipes
}

fun saveRecipe(recipeRequest: RecipeRequest): RecipeRequest? {
    var newRecipe: RecipeRequest? = RecipeRequest()
    val callNewRecipe = RetrofitClient.getRecipeService()
        .saveRecipe(recipeRequest)

    callNewRecipe.enqueue(object : Callback<RecipeRequest>{
        override fun onResponse(
            p0: Call<RecipeRequest?>,
            response: Response<RecipeRequest?>
        ){
            newRecipe = response.body() ?: null
        }

        override fun onFailure(
            p0: Call<RecipeRequest?>,
            p1: Throwable
        ) {
            println(p1.message)
        }
    })

    return newRecipe
}


//fun getRecipesByCategory(id: Int) = getAllRecipes()
//    .filter { recipe ->
//        recipe.category.id == id
//    }



