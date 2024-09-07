package com.app.categories.common

import com.app.categories.model.Categories

sealed class UiState {
    data object Loading : UiState()
    data class Success(val categoriesData: List<Categories>) : UiState()

    data class Error(val message: String) : UiState()
}