package br.com.fiap.recipesfood.ui.theme.screens

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme
import br.com.fiap.recipesfood.R
import br.com.fiap.recipesfood.components.CategoryItem
import br.com.fiap.recipesfood.components.RecipeItem
import br.com.fiap.recipesfood.navigation.Destination
import br.com.fiap.recipesfood.repository.RoomUserRepository
import br.com.fiap.recipesfood.repository.UserRepository
import br.com.fiap.recipesfood.repository.getAllCategories
import br.com.fiap.recipesfood.repository.getAllRecipes
import br.com.fiap.recipesfood.utils.convertByteArrayToBitmap

@Composable
fun HomeScreen(navController: NavController, email: String?) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = { MyTopAppBar(email.orEmpty(),navController)},
            bottomBar = {MyBottomAppBar()},
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add button"
                    )
                }
            }
        ) { paddingValues ->
            ContentScreen(
                modifier = Modifier.padding(paddingValues),
                        navController = navController
            )
            }
        }
    }

@Preview
@Composable
private fun HomeScreenPreview() {
    RecipesFoodTheme {
        HomeScreen(rememberNavController(), "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(email: String = "", rememberNavController: NavController,) {

    val userRepository: UserRepository =
        RoomUserRepository(LocalContext.current)

    val user = userRepository.getUserByEmail(email)

    var profileBitmap by remember {
        mutableStateOf<Bitmap>(
            convertByteArrayToBitmap(user!!.userImage!!)
        )
    }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
            //.height(60.dp),
        title = {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = "Hello, ${user!!.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = email,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
                Card(
                    modifier = Modifier
                        .size(48.dp),
                    shape = CircleShape,
                    colors = CardDefaults
                        .cardColors(
                            containerColor = Color.Transparent
                        ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Image(
                        bitmap = profileBitmap.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "User Image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun MyTopAppBarPreview() {
    RecipesFoodTheme {
        MyTopAppBar("", rememberNavController())
    }
}


data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MyBottomAppBar() {
    val  items = listOf(
        BottomNavigationItem("Home", icon = Icons.Default.Home),
        BottomNavigationItem("Favorite", icon = Icons.Default.Favorite),
        BottomNavigationItem("Profile", icon = Icons.Default.Person),
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        items.forEach {item ->
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MyBottomAppBarPreview() {
    RecipesFoodTheme {
        MyBottomAppBar()
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    // Variável que vai armazenar a lista de categorias

    val categories = getAllCategories()

    // Variável que vai armazenar a lista de receitas

    val recipes = getAllRecipes()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    unfocusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xfff5f5f5),
                    //focusedContainerColor = Color.LightGray,

                ),
            label = {
                Text(
                    text = stringResource(R.string.search_by_recipes),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
                .height(116.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.kitchenchafing),
                contentDescription = "Kitchen chafing",
                        modifier = Modifier
                        .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp),
            text = stringResource(R.string.categories),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories){category ->
                CategoryItem(
                    category = category,
                    onClick = {
                    navController.navigate(
                        route = Destination
                            .CategoryRecipeScreen
                            .createRoute(categoryId = category.id)
                    )
                })
            }
        }
        Text(
            text = "Newly added recipes",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recipes){recipe ->
                RecipeItem(recipe)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ContentScreenPreview() {
    RecipesFoodTheme{
        ContentScreen(navController = rememberNavController())
    }
}









