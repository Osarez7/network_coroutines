package com.example.networkcoroutines.view

import com.example.networkcoroutines.network.MarvelApiFactory
import kotlinx.coroutines.*

class MainPresenter{

    private var mainView: MainView? = null

     fun fetchCharacters() {
         GlobalScope.launch(Dispatchers.Main) {
             val response = MarvelApiFactory.marvelApi.getCharacters()
             mainView?.onFetchCharacters(response?.data?.results)
         }
    }

    fun attachView(view: MainView){
        mainView = view
    }

    fun detachView(){
        mainView = null
    }

}