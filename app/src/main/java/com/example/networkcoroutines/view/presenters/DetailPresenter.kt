package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.common.getSingleCharacter
import com.example.networkcoroutines.common.toCharacterDetail
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.MarvelResponse
import com.example.networkcoroutines.view.views.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter {

    private var detailView: DetailView? = null

    private val callback = object : Callback<MarvelResponse<Character>> {

        override fun onFailure(call: Call<MarvelResponse<Character>>, t: Throwable) {
            detailView?.onError(t.message ?: "Error")
        }

        override fun onResponse(call: Call<MarvelResponse<Character>>, response: Response<MarvelResponse<Character>>) {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.getSingleCharacter()?.let { character ->

                    MarvelApiFactory.marvelApi.getComicsByCharacterId(character.id)
                        .enqueue(object : Callback<MarvelResponse<Comic>> {
                            override fun onFailure(call: Call<MarvelResponse<Comic>>, t: Throwable) {
                                detailView?.onError(t.message ?: "Error")
                            }

                            override fun onResponse(call: Call<MarvelResponse<Comic>>, response: Response<MarvelResponse<Comic>>) {
                                if (response.isSuccessful && response.body() != null) {

                                    response.body()?.let {
                                        val characerDetail = character.toCharacterDetail(it.data.results)
                                        detailView?.onCharacterDetailResult(characerDetail)
                                    }

                                } else {
                                    detailView?.onError(response.errorBody()?.string() ?: "Error")
                                }
                            }

                        })

                }

            } else {
                detailView?.onError(response.errorBody()?.string() ?: "Error")
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
