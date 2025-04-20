package com.example.jetshopping.ui.home

import android.icu.util.ULocale.Category
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetshopping.Graph
import com.example.jetshopping.data.room.ItemsWithStoreAndList
import com.example.jetshopping.ui.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale.Category

class HomeViewModel(
    private val repository: Repository=Graph.repository
) : ViewModel(){
    var state by mutableStateOf(HomeState())
        private set
    private fun getItems(){
        viewModelScope.launch {
            repository.getItemsWithListAndStore.collectLatest{
                state = state.copy(
                    items = it
                )
            }
        }
    }
}

data class HomeState(
    val items: List<ItemsWithStoreAndList> = emptyList(),
    val category: Category = Category(),
    val itemChecked: Boolean = false

)