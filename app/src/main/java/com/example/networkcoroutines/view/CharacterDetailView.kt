package com.example.networkcoroutines.view

import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Event

interface CharacterDetailView{
    fun onCharacterDetailResult(character: Character)
    fun updateEvents(comics: List<Event>)
    fun updateComics(events: List<Comic>)
    fun onError(message: String)
}