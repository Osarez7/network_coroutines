package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.view.models.Movie

interface MainView{
    fun showError(message: String)
    fun onMoviesResult(images: List<Movie>)
}