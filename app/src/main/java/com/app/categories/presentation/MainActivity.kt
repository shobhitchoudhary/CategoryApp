package com.app.categories.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.app.categories.R
import com.app.categories.common.Section
import com.app.categories.common.UiState
import com.app.categories.common.gone
import com.app.categories.common.loadImage
import com.app.categories.common.visible
import com.app.categories.databinding.ActivityMainBinding
import com.app.categories.model.Categories
import com.app.categories.model.CategoriesItem
import com.app.categories.presentation.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        categoryViewModel.categoryMutableLiveData.observe(this) {
            renderResponseData(it)
        }
    }

    private fun renderResponseData(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressBar.visible()
                binding.errorMessageText.gone()
            }

            is UiState.Success -> {
                binding.progressBar.gone()
                binding.errorMessageText.gone()
                loadCategoriesData(uiState.categoriesData)
            }

            is UiState.Error -> {
                binding.progressBar.gone()
                binding.errorMessageText.visible()
                binding.errorMessageText.text = uiState.message
            }
        }
    }

    private fun loadCategoriesData(datas: List<Categories>?) {
        if (datas.isNullOrEmpty()) return
        for (section in datas) {
            when (section.sectionType) {
                Section.BANNER.sectionType -> {
                    val url = section.items.first().image
                    binding.bannerImageView.loadImage(url)
                }

                Section.SPLIT_BANNER.sectionType -> {
                    val lhsBanner = section.items.first()
                    binding.leftBannerImage.loadImage(lhsBanner.image)
                    binding.leftBannerText.text = lhsBanner.title

                    val rhsBanner = section.items.last()
                    binding.rightBannerImage.loadImage(rhsBanner.image)
                    binding.rightBannerText.text = rhsBanner.title
                }

                Section.HORIZONTAL_SCROLL.sectionType -> {
                    horizontalBlock(section.items)
                }
            }
        }
    }

    private fun horizontalBlock(items: List<CategoriesItem>?) {
        if (items.isNullOrEmpty()) return
        binding.innerLayout.removeAllViews()
        items.forEach { block ->
            val inflater = LayoutInflater.from(this)
            val itemView = inflater.inflate(R.layout.block_single_row, binding.innerLayout, false)

            // Image
            val itemImage = itemView.findViewById<AppCompatImageView>(R.id.blockImage)
            itemImage.loadImage(block.image)

            // Text
            val itemText = itemView.findViewById<AppCompatTextView>(R.id.blockText)
            itemText.text = block.title

            binding.innerLayout.addView(itemView)
        }
    }
}
