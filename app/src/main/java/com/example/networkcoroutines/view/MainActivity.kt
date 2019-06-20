package com.example.networkcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.networkcoroutines.R
import com.example.networkcoroutines.network.Character
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private var presenter: MainPresenter? = null

    private var marvelCharactersAdapter: MarvelCharactersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()

        marvelCharactersAdapter = MarvelCharactersAdapter()
        list_characters.adapter = marvelCharactersAdapter
        list_characters.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        btn_request_character.setOnClickListener {

                presenter?.fetchCharacters()

        }
    }


    override fun onFetchCharacters(characters: List<Character>?) {

        characters?.let {
            marvelCharactersAdapter?.charactersList = characters
            marvelCharactersAdapter?.notifyDataSetChanged()
        }
    }


    override fun onStart() {
        super.onStart()
        presenter?.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }
}
