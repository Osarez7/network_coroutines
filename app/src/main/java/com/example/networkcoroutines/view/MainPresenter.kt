package com.example.networkcoroutines.view

import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.MarvelResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter : CoroutineScope {

    private var mainView: MainView? = null
    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    fun searchCharacters(characterName: String) {
        mainView?.showProgressBar()
        launch {
            try {
                val charactersResponse: MarvelResponse<Character> =  MarvelApiFactory.marvelApi.getCharactersByName(characterName)
                mainView?.onFetchCharacters(charactersResponse.data.results)
            } catch (e: Exception) {
                mainView?.onError(e.message ?: "Error")
            }finally {
                mainView?.hideProgressBar()
            }
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