package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Character

interface DetailView{
    fun onCharacterDetailResult(character: Character)
    fun onComicsResult(comics: List<Comic>)
    fun onError(message: String)
}