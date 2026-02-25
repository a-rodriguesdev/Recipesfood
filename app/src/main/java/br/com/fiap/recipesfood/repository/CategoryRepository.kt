package br.com.fiap.recipesfood.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.fiap.recipesfood.factory.RetrofitClient
import br.com.fiap.recipesfood.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun getAllCategories(): List<Category> {

    var categories by remember {
        mutableStateOf(listOf<Category>())
    }

    val callCategories = RetrofitClient.getCategoryService().getAllCategories()

    callCategories.enqueue(object: Callback<List<Category>>{
        override fun onResponse(
            call: Call<List<Category>?>,
            response: Response<List<Category>?>
        ) {
            categories = response.body()!!
        }

        override fun onFailure(
            p0: Call<List<Category>?>,
            p1: Throwable
        ) {
            println(p1.printStackTrace())
        }

    })

    return categories

}


//fun getAllCategories() = listOf<Category>(
//    Category(id = 1000, name = "Chicken",
//        image = R.drawable.chicken),
//
//    Category(id = 2000, name = "Beef",
//        image = R.drawable.beef),
//
//    Category(id = 3000, name = "Fish",
//        image = R.drawable.fish),
//
//    Category(id = 4000, name = "Bakery",
//        image = R.drawable.bakery),
//
//    Category(id = 5000, name = "Vegetable",
//        image = R.drawable.vegetables),
//
//    Category(id = 6000, name = "Desserts",
//        image = R.drawable.dessert),
//
//    Category(id = 7000, name = "Drinks",
//        image = R.drawable.drink)
//)

@Composable
fun getCategoryById(id: Int) = getAllCategories()
    .find { category ->
        category.id == id
    }