package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.common.Constants
import com.example.networkcoroutines.common.getPoster
import com.example.networkcoroutines.common.toMovie
import com.example.networkcoroutines.common.toMovieDetail
import com.example.networkcoroutines.network.managers.TmdbManager
import com.example.networkcoroutines.view.views.DetailView
import kotlinx.coroutines.*

class DetailPresenter(
    private val tmbd: TmdbManager = TmdbManager(Constants.tmdbAoiKey)
) {
    private var detailView: DetailView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacterDetail(characterId: Int) = scope.launch {
//        try {

            val movie = withContext(Dispatchers.IO) {
                supervisorScope {
                    val movieResponse = async { tmbd.api.summary(characterId, Constants.DEFAULT_LANGUAGE) }
                    val imagesResponse = async { tmbd.api.images(characterId, Constants.DEFAULT_LANGUAGE) }

                    movieResponse.await().toMovieDetail(imagesResponse.await().getPoster())
                }
            }
            detailView?.onMovierDetailResult(movie)

//        } catch (e: Exception) {
//            detailView?.onError(e.message ?: e.toString())
//        }
    }

    fun attachView(view: DetailView) {
        detailView = view
    }

    fun cleanUp() {
        detailView = null
        job.cancel()
    }
}
