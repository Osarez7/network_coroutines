package com.example.networkcoroutines.view.presenters

import android.util.Log
import com.example.networkcoroutines.common.Constants
import com.example.networkcoroutines.common.getPoster
import com.example.networkcoroutines.common.toMovie
import com.example.networkcoroutines.network.managers.TmdbManager
import com.example.networkcoroutines.network.managers.TraktV2Manager
import com.example.networkcoroutines.view.views.MainView
import com.uwetrottmann.trakt5.enums.Extended
import kotlinx.coroutines.*

class MainPresenter(
    val trakt: TraktV2Manager = TraktV2Manager(Constants.apiKey), val tmbd: TmdbManager = TmdbManager(Constants.tmdbAoiKey)
) {

    companion object {
        const val TAG = "MainPresenter"
    }

    private var mainView: MainView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacters(name: String) = scope.launch {

        val movies = withContext(Dispatchers.IO) {

            val trendingMovies = trakt.api.trending(1, null, Extended.FULL)

            trendingMovies.map {
                val images = tmbd.api.images(it.movie.ids.tmdb, Constants.DEFAULT_LANGUAGE)
                Log.d(TAG, "===POSTER" + images.getPoster())
                it.movie.toMovie(images.getPoster())
            }
        }

        mainView?.onMoviesResult(movies)
    }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun cleanUp() {
        mainView = null
        job.cancel()
    }
}

