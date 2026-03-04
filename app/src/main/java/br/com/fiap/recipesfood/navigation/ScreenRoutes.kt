package br.com.fiap.recipesfood.navigation

sealed class Destination(val route: String){

    object InitialScreen: Destination("initial")
    object SignupScreen: Destination("signup")


    object AddRecipeScreen: Destination("addRecipeScreen")

    object ProfileScreen: Destination("profile/{email}"){
        fun createRoute(email: String): String{
            return "profile/$email"
        }
    }


    object HomeScreen: Destination("home/{email}"){
        fun createRoute(email: String): String{
            return "home/$email"
        }
    }
    object LoginScreen: Destination("login")

    object CategoryRecipeScreen : Destination(
        route = "categoryRecipes/{Id}"
    ) {
        fun createRoute(id: Int): String {
            return "categoryRecipes/$id"
        }
    }

    // Rota para a tela de cadastro de ingredientes da receita
// passando o id e nome da receita como argumentos da rota
    object AddRecipeIngredientsScreen : Destination(
        route = "addIngredients/{recipeId}/{recipeName}"
    ) {
        fun createRoute(
            recipeId: Int,
            recipeName: String
        ): String {
            return "addIngredients/$recipeId/$recipeName"
        }
    }
}