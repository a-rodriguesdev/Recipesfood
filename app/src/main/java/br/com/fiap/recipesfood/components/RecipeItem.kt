package br.com.fiap.recipesfood.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.recipesfood.factory.RetrofitClient
import br.com.fiap.recipesfood.model.Recipe
import br.com.fiap.recipesfood.repository.getLatestRecipes
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme
import coil.compose.AsyncImage

@Composable
fun RecipeItem(recipe: Recipe) {

    val serverBaseUrl = RetrofitClient.BASE_URL.removeSuffix("api/")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val imagePath = if (recipe.image.isNotBlank()) recipe.image else recipe.category.image

            val imageUrl = when {
                imagePath.startsWith("http") -> imagePath
                imagePath.startsWith("/") -> serverBaseUrl.dropLast(1) + imagePath
                else -> serverBaseUrl + imagePath
            }

            AsyncImage(
                model = imageUrl,
                contentDescription = recipe.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF212121), Color(0x00212121)
                            )
                        )
                    )
            ) {
                Text(
                    text = "Nome do usuário",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x11212121), Color(0xff212121)
                            )
                        )
                    )
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Row(
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${recipe.cookingTime} min",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipeItemPreview() {
    RecipesFoodTheme {
        RecipeItem(getLatestRecipes()[1])
    }
}