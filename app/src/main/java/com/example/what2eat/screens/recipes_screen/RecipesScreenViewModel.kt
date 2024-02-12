package com.example.what2eat.screens.recipes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.what2eat.domain.model.recipes_list.Recipe
import com.example.what2eat.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecipesScreenViewModel @Inject constructor(
    private val recipesUseCase: GetRecipesUseCase
) : ViewModel() {
    val recipes: Flow<PagingData<Recipe>> = Pager(PagingConfig(pageSize = 20)) {
        recipesUseCase
    }.flow.cachedIn(viewModelScope)
}