package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.view.views.DetailView
import com.example.networkcoroutines.view.views.MainView
import kotlinx.coroutines.*

class DetailPresenter {

    companion object {
        private const val FIRST_RESULT_INDEX = 0
    }

    private var detailView: DetailView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacterDetail(characterId: Long) = scope.launch {
        try {
            supervisorScope {
                val characterResponse = async { MarvelApiFactory.marvelApi.getCharacterById(characterId) }
                val comicsResponse = async { MarvelApiFactory.marvelApi.getComicsByCharacterId(characterId) }
                detailView?.displayCharacterDetails(
                    characterResponse.await().data.results[FIRST_RESULT_INDEX],
                    comicsResponse.await().data.results
                )

            }

        } catch (e: Exception) {
            detailView?.onError(e.message ?: "Error")
        }
    }

    fun attachView(view: DetailView) {
        detailView = view
    }

    fun cleanUp() {
        detailView = null
        job.cancel()
    }
}
