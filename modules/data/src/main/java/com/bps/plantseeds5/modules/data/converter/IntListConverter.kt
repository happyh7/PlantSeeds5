package com.bps.plantseeds5.modules.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntListConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Int>>() {}.type

    @TypeConverter
    fun fromString(value: String): List<Int> {
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return gson.toJson(list)
    }
} 