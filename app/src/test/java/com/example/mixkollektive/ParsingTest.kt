package com.example.mixkollektive

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Test
import java.util.concurrent.ThreadLocalRandom


internal class ParsingTest {

    @Test
    fun firstTest() {
        val stream = this.javaClass.getResourceAsStream("/tracks.json")
        val jsonString = stream
            .bufferedReader()
            .use { it.readText() }
        val gson = Gson()
        val listTacksType = object : TypeToken<List<Item>>() {}.type

        var items: List<Item> = gson.fromJson(jsonString, listTacksType)

        println("These are the items")
        println(items)
    }
}