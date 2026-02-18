package br.com.fiap.recipesfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.recipesfood.navigation.NavigationRoutes
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme
import br.com.fiap.recipesfood.ui.theme.screens.HomeScreen
import br.com.fiap.recipesfood.ui.theme.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesFoodTheme {
                NavigationRoutes()
            }
        }
    }
}

