package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.view.models.CharacterDetail

interface DetailView{
    fun onCharacterDetailResult(character: CharacterDetail)
    fun onError(message: String)
}