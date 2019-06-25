package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.MarvelResponse
import com.example.networkcoroutines.view.views.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter {

   companion object {
       private const val FIRST_RESULT_INDEX = 0
   }
    private var detailView: DetailView? = null


    private val comicsCallback = object : Callback<MarvelResponse<Comic>> {
        override fun onFailure(call: Call<MarvelResponse<Comic>>, t: Throwable) {
            detailView?.onError(t.message ?: "Error")
        }

        override fun onResponse(call: Call<MarvelResponse<Comic>>, response: Response<MarvelResponse<Comic>>) {
            if(response.isSuccessful){
              detailView?.onComicsResult(response.body()?.data?.results)

            }else{
               detailView?.onError( response.errorBody()?.string() ?: "Error")
            }
        }

    }

    private val callback = object : Callback<MarvelResponse<Character>> {
        override fun onFailure(call: Call<MarvelResponse<Character>>, t: Throwable) {
            detailView?.onError(t.message ?: "Error")
        }

        override fun onResponse(call: Call<MarvelResponse<Character>>, response: Response<MarvelResponse<Character>>) {

            response.body()?.data?.results?.get(FIRST_RESULT_INDEX)?.let {
                detailView?.onCharacterDetailResult(it)
                MarvelApiFactory.marvelApi.getComicsByCharacterId(it.id).enqueue(comicsCallback)
            }
        }

    }


    fun fetchCharacterDetail(characterId: Long) {
            MarvelApiFactory.marvelApi.getCharacterById(characterId).enqueue(callback)
    }


    fun attachView(view: DetailView) {
        detailView = view
    }

    fun cleanUp() {
        detailView = null
    }
}
