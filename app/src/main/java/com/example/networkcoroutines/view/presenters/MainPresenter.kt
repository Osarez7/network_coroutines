package com.example.networkcoroutines.view.presenters

import android.util.Log
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.view.views.MainView
import kotlinx.coroutines.*

class MainPresenter {

    companion object {
        const val TAG = "MainPresenter"
    }
    private var mainView: MainView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacters(name: String) = scope.launch {
            try {
                val charactersResponse =  MarvelApiFactory.marvelApi.getCharactersByName(name)
                mainView?.onFetchCharacters(charactersResponse.data.results)
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                mainView?.showError(e.message ?: "Error")
            }
    }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun cleanUp() {
        mainView = null
        job.cancel()
    }

}