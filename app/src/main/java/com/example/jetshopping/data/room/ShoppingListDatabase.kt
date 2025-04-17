package com.example.jetshopping.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetshopping.data.room.models.Item
import com.example.jetshopping.data.room.models.ShoppingList
import com.example.jetshopping.data.room.models.Store


@Database(
    entities = [ShoppingList ::class, Item::class, Store::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDatabase : RoomDatabase()  {
    abstract fun listDao(): ListDao
    abstract fun itemDao(): ItemDao
    abstract fun storeDao(): StoreDao

    companion object{
        @Volatile
        var INSTNCE: ShoppingListDatabase? = null
        fun getDatabase(context: Context): ShoppingListDatabase {
            return INSTNCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ShoppingListDatabase::class.java,
                    "shopping_db"
                ).build()
                INSTNCE= instance
                return instance
            }
        }
    }
}