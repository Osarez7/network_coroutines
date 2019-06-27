package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.Trakt_
import com.example.networkcoroutines.view.views.DetailView
import com.example.networkcoroutines.view.views.MainView
import com.uwetrottmann.trakt5.TraktV2
import com.uwetrottmann.trakt5.enums.Extended
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

           val showsResponse =  TraktV2(Trakt_.apiKey)
            .shows().trending(1, null, Extended.FULL).execute()

//            supervisorScope {
//                val characterResponse = async { MarvelApiFactory.marvelApi.getCharacterById(characterId) }
//                val comicsResponse = async { MarvelApiFactory.marvelApi.getComicsByCharacterId(characterId) }
//                detailView?.displayCharacterDetails(
//                    characterResponse.await().data.results[FIRST_RESULT_INDEX],
//                    comicsResponse.await().data.results
//                )
//
//            }

            val shows = showsResponse.body()

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
