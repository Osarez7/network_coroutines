package com.example.networkcoroutines.view.presenters

import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.MarvelApiFactory
import com.example.networkcoroutines.network.MarvelResponse
import com.example.networkcoroutines.view.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter {

    private var mainView: MainView? = null

    private val callback = object : Callback<MarvelResponse<Character>>{
        override fun onFailure(call: Call<MarvelResponse<Character>>, t: Throwable) {
            mainView?.showError(t.message ?: "Error")
        }

        override fun onResponse(call: Call<MarvelResponse<Character>>, response: Response<MarvelResponse<Character>>) {
           mainView?.onFetchCharacters(response.body()?.data?.results)
        }

    }

    fun fetchCharacters(name: String) {
        MarvelApiFactory.marvelApi.getCharactersByName(name).enqueue(callback)
    }

    fun attachView(view: MainView) {
        mainView = view
    }

    fun cleanUp() {
        mainView = null
    }



}

