package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.common.getSingleCharacter
import com.example.networkcoroutines.common.toCharacterDetail
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.view.views.DetailView
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
            val characterDetail =  supervisorScope {
                val characterResponse = async { MarvelApiFactory.marvelApi.getCharacterById(characterId) }
                val comicsResponse = async { MarvelApiFactory.marvelApi.getComicsByCharacterId(characterId) }
                characterResponse.await().getSingleCharacter()
                    .toCharacterDetail(comicsResponse.await().data.results)
            }

            detailView?.displayCharacterDetails(characterDetail)

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
