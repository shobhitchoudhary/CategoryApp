package com.app.categories.network

import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getCategory() = apiService.getCategories()
}