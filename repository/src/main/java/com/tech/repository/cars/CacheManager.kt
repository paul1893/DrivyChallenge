package com.tech.repository.cars

import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.FileReader


class CacheManager(
    private val context: Context
) {

    private val file = File(context.cacheDir, "cars.json")

    fun get() : List<CarJSON> {
        val gson = Gson()
        val reader = JsonReader(FileReader(file))
        return gson.fromJson<List<CarJSON>>(reader, object : TypeToken<List<CarJSON>>() {}.type)
    }

    fun save(cars: List<CarJSON>) {
        if (!file.exists()) {
            file.createNewFile()
        }
        FileWriter(file.absolutePath).use { writer ->
            val gson = GsonBuilder().create()
            gson.toJson(cars, writer)
        }
    }
}