package com.app.categories.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("sectionType")
    val sectionType: String,
    val items: List<CategoriesItem>
)

data class CategoriesItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String
)
