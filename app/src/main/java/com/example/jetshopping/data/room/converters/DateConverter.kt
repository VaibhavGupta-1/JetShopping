package com.example.jetshopping.data.room.converters

import java.util.Date

open class DateConverter{
    fun toDate(date: Long?): Date?{
        return date?.let{Date(it)}
    }
}