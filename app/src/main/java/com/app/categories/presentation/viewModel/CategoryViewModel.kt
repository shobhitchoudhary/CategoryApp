package com.app.categories.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.categories.common.UiState
import com.app.categories.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(repository: CategoriesRepository) :
    ViewModel() {
    val categoryMutableLiveData: MutableLiveData<UiState> = MutableLiveData()

    init {
        repository.getCategory()
            .onStart {
                categoryMutableLiveData.value = UiState.Loading
            }
            .onEach {
                categoryMutableLiveData.value = UiState.Success(it)
            }
            .catch { throwable ->
                when (throwable) {
                    is HttpException -> {
                        categoryMutableLiveData.value =
                            UiState.Error(throwable.localizedMessage ?: "An Unknown error occurred")
                    }

                    is IOException -> {
                        categoryMutableLiveData.value =
                            UiState.Error(throwable.localizedMessage ?: "Check Connectivity")
                    }

                    else -> {
                        categoryMutableLiveData.value =
                            UiState.Error(throwable.localizedMessage ?: "Network Error")
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}