package com.example.networkcoroutines.view.views

import com.example.networkcoroutines.view.models.MovieDetail

interface DetailView{
    fun onMovierDetailResult(movieDetail: MovieDetail)
    fun onError(message: String)
}