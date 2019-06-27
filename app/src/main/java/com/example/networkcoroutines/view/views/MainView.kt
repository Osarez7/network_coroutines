package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.network.Character

interface MainView{
   fun onFetchCharacters(characters: List<Character>?)
   fun showError(message: String)
   fun showProgressBar()
   fun hideProgressBar()
}