package br.com.fiap.recipesfood.repository

import br.com.fiap.recipesfood.R
import br.com.fiap.recipesfood.model.Category
import br.com.fiap.recipesfood.model.DifficultLevel
import br.com.fiap.recipesfood.model.Recipe
import br.com.fiap.recipesfood.model.User
import java.time.LocalDate

    fun getAllRecipes() = listOf<Recipe>(
        Recipe(
            id = 1,
            category = Category(id = 6000, name = "Desserts"),
            user = User(id = 100, name = "Ana Maria"),
            difficultLevel = DifficultLevel.BEGINNER,
            name = "Carrot Cake",
            description = "Moist, spiced, carrot-filled cake with tangy cream cheese frosting.",
            cookingTime = 60,
            createdAt = LocalDate.now(),
            image = R.drawable.carrot_cake
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
            image = R.drawable.hearts_of_palm_salad
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
            image = R.drawable.calabrese_bread
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
            image = R.drawable.vegetable_soup
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
            image = R.drawable.feijoada
        )

    )


fun getRecipesByCategory(id: Int) = getAllRecipes()
    .filter { recipe ->
        recipe.category.id == id
    }



