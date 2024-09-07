package com.app.categories.repository

import com.app.categories.model.Categories
import com.app.categories.network.ApiServiceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {
    fun getCategory(): Flow<List<Categories>> = flow {
        val response = apiServiceImpl.getCategory()
        emit(response)
    }
}