package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.view.models.CharacterDetail

interface DetailView{
    fun displayCharacterDetails(character: Character, comics: List<Comic>)
    fun onCharacterDetailResult(character: Character)
    fun onComicsResult(comics: List<Comic>)
    fun onError(message: String)
    fun displayCharacterDetails(character: CharacterDetail)
}