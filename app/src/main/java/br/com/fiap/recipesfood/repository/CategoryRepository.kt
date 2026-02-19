package br.com.fiap.recipesfood.repository

import androidx.compose.ui.graphics.Color
import br.com.fiap.recipesfood.R
import br.com.fiap.recipesfood.model.Category

fun getAllCategories() = listOf<Category>(
    Category(id = 1000, name = "Chicken",
        image = R.drawable.chicken),

    Category(id = 2000, name = "Beef",
        image = R.drawable.beef),

    Category(id = 3000, name = "Fish",
        image = R.drawable.fish),

    Category(id = 4000, name = "Bakery",
        image = R.drawable.bakery),

    Category(id = 5000, name = "Vegetable",
        image = R.drawable.vegetables),

    Category(id = 6000, name = "Desserts",
        image = R.drawable.dessert),

    Category(id = 7000, name = "Drinks",
        image = R.drawable.drink)
)