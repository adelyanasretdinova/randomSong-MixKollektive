package com.example.mixkollektive.remote

import com.example.mixkollektive.Item
import kotlin.random.Random

class FetchRandomSongDataSource {

    fun randomItem(list: List<Item>): Item {
        val randomIndex: Int = Random.nextInt(list.size)
        return list[randomIndex]
    }

}