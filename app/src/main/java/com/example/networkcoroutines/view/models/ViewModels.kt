package com.example.networkcoroutines.view.models

import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Thumbnail

data class CharacterDetail(
    val id: Long = 0,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: List<Comic>
)