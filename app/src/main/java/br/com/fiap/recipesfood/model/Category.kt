package br.com.fiap.recipesfood.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("categoryId") val id: Int = 0,
    @SerializedName("categoryName")val name: String = "Name",
    @SerializedName("url") val image: String = "",
    @SerializedName("color")val background: String = "FFFFFFFF"
)
