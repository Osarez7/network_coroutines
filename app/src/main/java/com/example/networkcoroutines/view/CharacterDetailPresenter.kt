package com.example.networkcoroutines.view

import android.util.Log
import com.example.networkcoroutines.network.MarvelApiFactory
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CharacterDetailPresenter : CoroutineScope {

    companion object {
        const val TAG = "CharacterDetailPresenter"
    }

    private var detailView: CharacterDetailView? = null
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    fun fetchCharacter(characterId: Long) {

        launch {
            try {

                supervisorScope {
                    val charactersResponse = async { MarvelApiFactory.marvelApi.getCharacterById(characterId) }
                    val comicsResponse = async { MarvelApiFactory.marvelApi.getComicsById(characterId) }

                    withContext(Dispatchers.Main) {
                        detailView?.onCharacterDetailResult(charactersResponse.await().data.results[0])
                        detailView?.updateComics(comicsResponse.await().data.results)
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "Error")
                withContext(Dispatchers.Main){
                    detailView?.onError(e.message ?: "Error")
                }
            }
        }
    }

    fun attachView(view: CharacterDetailView) {
        detailView = view
    }

    fun detachView() {
        detailView = null
        job.cancel()
    }

}