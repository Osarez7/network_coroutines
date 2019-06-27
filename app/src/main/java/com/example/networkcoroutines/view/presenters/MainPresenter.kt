package com.example.networkcoroutines.view.presenters

import android.util.Log
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.Thumbnail
import com.example.networkcoroutines.network.Trakt_
import com.example.networkcoroutines.view.views.MainView
import com.uwetrottmann.tmdb2.Tmdb
import com.uwetrottmann.trakt5.TraktV2
import com.uwetrottmann.trakt5.enums.Extended
import kotlinx.coroutines.*

class MainPresenter(val tmbd:Tmdb = Tmdb(Trakt_.tmdbAoiKey)) {

    companion object {
        const val TAG = "MainPresenter"
    }
    private var mainView: MainView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun fetchCharacters(name: String) = scope.launch {
//            try {
//                val charactersResponse =  MarvelApiFactory.marvelApi.getCharactersByName(name)
//                mainView?.onFetchCharacters(charactersResponse.data.results)
//            } catch (e: Exception) {
//                Log.e(TAG, e.message)
//                mainView?.showError(e.message ?: "Error")
//            }


        val shows = withContext(Dispatchers.IO){
            val showsResponse =  TraktV2(Trakt_.apiKey)
                .shows().trending(1, null, Extended.FULL).execute()


            showsResponse.body()?.map {
                 tmbd.moviesService().images(it.show?.ids?.tmdb ?: 1, "en").execute().body()
//
// if(!imageResponse?.posters.isNullOrEmpty()){
//
//                    Character(1, it.show?.title!!, "", Thumbnail("https://image.tmdb.org/t/p/w200/" , imageResponse?.posters?.get(0)?.file_path ?: ""))
//                }else{
//                    Character(0, "", "", Thumbnail("", ""))
//                }
            }
        }


        mainView?.onFetchShows(shows)
    }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun cleanUp() {
        mainView = null
        job.cancel()
    }

}