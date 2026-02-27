package br.com.fiap.recipesfood.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.recipesfood.factory.RetrofitClient
import br.com.fiap.recipesfood.model.Category
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme
import coil.compose.AsyncImage

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit = {}
) {

    val baseUrl = RetrofitClient.BASE_URL.plus("recipes")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            .clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(96.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                val context = LocalContext.current

                val imageResId = remember(category.name) {
                    val fileName = category.name.lowercase()
                    context.resources.getIdentifier(
                        fileName, "drawable", context
                            .packageName)
                }

                if (imageResId != 0) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = category.name,
                        modifier = Modifier.size(75.dp)
                    )
                } else {
                    // fallback: usa imagem da API (professor)
                    AsyncImage(
                        model = category.image, // url da API
                        contentDescription = category.name,
                        modifier = Modifier.size(65.dp)
                    )
                }

//                Image(
//                    painter = painterResource(category.image!!),
//                    contentDescription = category.name,
//                    modifier = Modifier
//                        .size(80.dp)
//                )
            }
        }
        Text(
            text = category.name,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    RecipesFoodTheme {
        CategoryItem(
            category = Category(
                id = 1,
                name = "Chicken",
                image = "chicken",
                background = "FFFFFFFF"
            )
        )
    }
}