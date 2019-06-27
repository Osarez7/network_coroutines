package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.network.Character
import com.uwetrottmann.tmdb2.entities.Images

interface MainView{
   fun  onFetchCharacters(characters: List<Character>?)
    fun showError(message: String)
    fun onFetchShows(images: List<Images?>?)
}