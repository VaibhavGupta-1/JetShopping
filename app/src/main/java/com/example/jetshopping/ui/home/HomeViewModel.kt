package com.example.jetshopping.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetshopping.Graph
import com.example.jetshopping.data.room.ItemsWithStoreAndList
import com.example.jetshopping.data.room.models.Item
import com.example.jetshopping.ui.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

enum class Category {
    ALL,
    GROCERY,
    CLOTHING,
    ELECTRONICS,
    HOME,
    OTHER
}

class HomeViewModel(
    private val repository: Repository=Graph.repository
) : ViewModel(){
    var state by mutableStateOf(HomeState())
        private set


    init {
        getItems()
    }


    private fun getItems(){
        viewModelScope.launch {
            repository.getItemsWithListAndStore.collectLatest{
                state = state.copy(
                    items = it
                )
            }
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
    fun onCategoryChange(category: Category){
        state = state.copy(category = category)
        filterItems(10001)
    }

    fun onItemCheckedChange(item: Item, isChecked: Boolean){
        viewModelScope.launch {
            repository.updateItem(item.copy(ischecked = isChecked))
        }
    }



     private fun filterItems(shoppingListId:Int){
         if (shoppingListId != 10001) {
             viewModelScope.launch {
                 repository.getItemWithStoreAndListFilteredById(
                     shoppingListId
                 ).collectLatest {
                     state = state.copy(
                         items = it
                     )
                 }
             }
         }else{
             getItems()
         }
     }




    
}

data class HomeState(
    val items: List<ItemsWithStoreAndList> = emptyList(),
    val category: Category = Category.ALL,
    val itemChecked: Boolean = false
)