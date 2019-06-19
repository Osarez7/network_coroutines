package com.example.networkcoroutines.view

import com.example.networkcoroutines.common.Constants
import com.example.networkcoroutines.common.toMD5
import com.example.networkcoroutines.network.MarvelApiClient
import com.example.networkcoroutines.network.MarvelApiService
import kotlinx.coroutines.*
import java.util.*

class MainPresenter{

    private var mainView: MainView? = null
    private var service: MarvelApiService? = null

    init {
        service = MarvelApiClient.client?.create(MarvelApiService::class.java)
    }

    fun attachView(view: MainView){
        mainView = view
    }


     fun dettachView(){
        mainView = null
    }

     fun fetchCharacteres() {
        val timestamp = Date().time.toString()
        val hash = (timestamp + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY).toMD5()

         GlobalScope.launch(Dispatchers.Main) {
             val response = service?.getCharacters(Constants.PUBLIC_KEY, hash, timestamp)
             mainView?.onFetchCharacters(response?.data?.results)
         }
    }
}