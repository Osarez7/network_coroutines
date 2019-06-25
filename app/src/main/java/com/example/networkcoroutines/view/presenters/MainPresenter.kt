package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.view.views.MainView
import kotlinx.coroutines.*

class MainPresenter {

    private var mainView: MainView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacters(name: String) = scope.launch {
            val charactersResponse =  MarvelApiFactory.marvelApi.getCharactersByName(name)
            mainView?.onFetchCharacters(charactersResponse?.data?.results)
        }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun cleanUp() {
        mainView = null
        job.cancel()
    }

}