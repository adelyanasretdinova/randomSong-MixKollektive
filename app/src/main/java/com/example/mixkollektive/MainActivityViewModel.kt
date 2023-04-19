package com.example.mixkollektive

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixkollektive.remote.FetchRandomSongDataSource
import com.example.mixkollektive.remote.PlaylistDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val playlistDataSource = PlaylistDataSource()
    private val fetchRandomSongDataSource = FetchRandomSongDataSource()
    private val _playlistData = MutableStateFlow<List<Item>>(listOf())
    private val _oneSongData = mutableStateOf<Item?>(null)

    fun currentSong() = _oneSongData.value

    fun initializeViewModel(context: Context) {
        dataLoad(context = context)
    }

    private fun dataLoad(context: Context) {
        viewModelScope.launch {
            if (_playlistData.value.isEmpty()) {
                _playlistData.update { playlistDataSource.parseJson(context) }
            }
        }
    }

    fun generateNewRandomSong() {
        _oneSongData.value = fetchRandomSongDataSource.randomItem(_playlistData.value)
    }
}
