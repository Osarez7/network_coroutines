package com.example.networkcoroutines.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkcoroutines.R
import com.example.networkcoroutines.common.loadImage
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.view.adapters.ComicsAdapter
import com.example.networkcoroutines.view.presenters.DetailPresenter
import com.example.networkcoroutines.view.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import android.widget.Toast


class DetailActivity : AppCompatActivity(), DetailView {


    private lateinit var presenter: DetailPresenter
    private lateinit var comicsAdapter: ComicsAdapter


    companion object {
        const val EXTRA_CHARACTER_ID = "extra_character_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter()
        presenter.attachView(this)


        comicsAdapter = ComicsAdapter()
        list_comics.adapter = comicsAdapter
        list_comics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val characterId = intent.getLongExtra(EXTRA_CHARACTER_ID, 0)
        presenter.fetchCharacterDetail(characterId)
    }


    override fun onComicsResult(comics: List<Comic>) {
        comicsAdapter.comicsList = comics
        comicsAdapter.notifyDataSetChanged()
    }

    override fun onCharacterDetailResult(character: Character) {
        img_character.loadImage("${character.thumbnail.path}.${character.thumbnail.extension}")
        txt_name.text = character.name
        txt_description.text = character.description
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }


    override fun displayCharacterDetails(character: Character, comics: List<Comic>) {
        onCharacterDetailResult(character)
        onComicsResult(comics)
    }
}
