package com.example.jetshopping.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "shopping_list")
data class ShoppingList(
    @ColumnInfo(name = "list_id")
    @PrimaryKey
    val id: Int,
    val name: String
)

@Entity(tableName = "items")
data class Item(
    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val itemName: String,
    val qty: String,
    val listId: Int,
    val storeIdfk: Int,
    val date: Date,
    val isChecked: Boolean,
    val ischecked: Boolean
)


@Entity(tableName = "stores")
data class Store(
    @ColumnInfo(name = "store_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val listIdfk: Int,
)