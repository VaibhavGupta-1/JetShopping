package com.example.jetshopping

import android.content.Context
import com.example.jetshopping.data.room.ShoppingListDatabase
import com.example.jetshopping.ui.repository.Repository

object Graph {
    lateinit var db: ShoppingListDatabase
        private set

    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            storeDao = db.storeDao(),
            itemDao = db.itemDao()
        )
    }

    fun provide(context: Context) {
        db= ShoppingListDatabase.getDatabase(context)
    }


}