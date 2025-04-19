package com.example.jetshopping.ui.repository

import com.example.jetshopping.data.room.ItemDao
import com.example.jetshopping.data.room.ListDao
import com.example.jetshopping.data.room.StoreDao

class Repository (
    private val listDao: ListDao,
    private val storeDao: StoreDao,
    private val itemDao: ItemDao
){
    val store= storeDao.getAllStores()
    val getItemsWithListAndStore = listDao.getItemsWithStoreAndList()

    fun getItemsWithStoreAndList(id: Int) = listDao.getItemsWithStoreAndListFilteredById(id)

    fun getItemWithStoreAndListFilteredById(id: Int) = listDao.getItemWithStoreAndListFilteredById(id)





}