package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Character

interface DetailView{
    fun displayCharacterDetails(character: Character, comics: List<Comic>)
    fun onCharacterDetailResult(character: Character)
    fun onComicsResult(comics: List<Comic>)
    fun onError(message: String)
}