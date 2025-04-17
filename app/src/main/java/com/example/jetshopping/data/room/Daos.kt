package com.example.jetshopping.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetshopping.data.room.models.Item
import com.example.jetshopping.data.room.models.ShoppingList
import com.example.jetshopping.data.room.models.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item:Item)

    @Delete
    suspend fun delete(item:Item)

    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE item_Id= :itemId")
    fun getItem(itemId: Int): Flow<Item>
}

@Dao
interface StoreDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(store:Store)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(store:Store)

    @Delete
    suspend fun delete(item:Item)

    @Query("SELECT * FROM stores")
    fun getAll(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE store_Id= :storeId")
    fun getStore(storeId: Int): Flow<Store>
}

@Dao
interface ListDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Query("""
        Select * from items AS I inner join shopping_list AS S 
        on I.storeIdfk = S.list_id inner join stores AS ST   
        on I.storeIdfk = St.store_id
    """)
    fun getItemsWithStoreAndList(): Flow<List<ItemsWithStoreAndList>>

    @Query("""
        Select * from items AS I inner join shopping_list AS S 
        on I.storeIdfk = S.list_id inner join stores AS ST   
        on I.storeIdfk = St.store_id where S.list_id = :listId
    """)
    fun getItemsWithStoreAndListFilteredById(listId: Int): Flow<List<ItemsWithStoreAndList>>

    @Query("""
        Select * from items AS I inner join shopping_list AS S 
        on I.storeIdfk = S.list_id inner join stores AS ST   
        on I.storeIdfk = St.store_id where I.item_id = :itemId
    """)
    fun getItemWithStoreAndListFilteredById(itemId: Int): Flow<ItemsWithStoreAndList>
}

data class ItemsWithStoreAndList(
    @Embedded val item: Item,
    @Embedded val shoppingList: ShoppingList,
    @Embedded val store: Store,
)