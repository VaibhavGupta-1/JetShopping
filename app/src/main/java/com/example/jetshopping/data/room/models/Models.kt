package com.example.jetshopping.data.room.models


@Entity(tableName = "shopping_list")
data class ShoppingList(
    @ColumnInfo(name = "list_id")
    @PrimaryKey(autoGenerate = true) val id: Int,
    val id: Int,
    val name: String,
    val items: List<ShoppingListItem>

) {
}