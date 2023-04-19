package com.example.mixkollektive.remote

import android.content.Context
import com.example.mixkollektive.Item
import com.example.mixkollektive.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class PlaylistDataSource {

    fun parseJson(context: Context): List<Item> {
        lateinit var jsonString: String
        try {
            val stream = context.resources.openRawResource(R.raw.tracks)
            jsonString = stream.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            println("Exception: $ioException")
        }
        val gson = Gson()
        val listTacksType = object : TypeToken<List<Item>>() {}.type

        return gson.fromJson(jsonString, listTacksType)
    }
}