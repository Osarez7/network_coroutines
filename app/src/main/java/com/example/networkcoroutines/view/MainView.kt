package com.example.networkcoroutines.view

import com.example.networkcoroutines.network.Character

interface MainView{
   fun  onFetchCharacters(characters: List<Character>?)
}