package com.app.categories.network

import com.app.categories.model.Categories
import retrofit2.http.GET

interface ApiService {
    @GET("5BEJ")
    suspend fun getCategories(): List<Categories>
}