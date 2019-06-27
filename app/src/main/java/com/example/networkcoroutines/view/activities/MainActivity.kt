package com.example.networkcoroutines.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.networkcoroutines.R
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Trakt_
import com.example.networkcoroutines.view.presenters.MainPresenter
import com.example.networkcoroutines.view.views.MainView
import com.example.networkcoroutines.view.adapters.CharactersAdapter
import com.uwetrottmann.tmdb2.Tmdb
import com.uwetrottmann.tmdb2.entities.Image
import com.uwetrottmann.tmdb2.entities.Images
import com.uwetrottmann.trakt5.entities.TrendingShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    private lateinit var presenter: MainPresenter

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()

        charactersAdapter = CharactersAdapter {
            showDetailScreen(it)
        }

        list_characters.adapter = charactersAdapter
        list_characters.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        btn_request_character.setOnClickListener {
            presenter.fetchCharacters(txt_input_name.text.toString())
        }


        presenter.attachView(this)
    }

    private fun showDetailScreen(characterId: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }


    override fun onFetchCharacters(characters: List<Character>?) {

        characters?.let {
            charactersAdapter.charactersList = characters
            charactersAdapter.notifyDataSetChanged()
        }
    }


    override fun showError(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onFetchShows(images: List<Images?>?) {
        images?.forEach {
            it?.posters?.forEach{
                Log.d("===Shows", it?.file_path ?: "-----")

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }
}
