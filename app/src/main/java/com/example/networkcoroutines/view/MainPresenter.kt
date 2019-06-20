package com.example.networkcoroutines.view

import com.example.networkcoroutines.network.MarvelApiFactory
import kotlinx.coroutines.*

class MainPresenter {

    private var mainView: MainView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacters(){
        scope.launch {
            val charactersResponse =  MarvelApiFactory.marvelApi.getCharacters()
            mainView?.onFetchCharacters(charactersResponse?.data?.results)
        }
    }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun detachView() {
        mainView = null
        job.cancel()
    }

}