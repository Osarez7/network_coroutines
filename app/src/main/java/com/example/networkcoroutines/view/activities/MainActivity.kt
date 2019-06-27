package com.example.networkcoroutines.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.networkcoroutines.R
import com.example.networkcoroutines.view.presenters.MainPresenter
import com.example.networkcoroutines.view.views.MainView
import com.example.networkcoroutines.view.adapters.MoviesAdapter
import com.example.networkcoroutines.view.models.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
   
    private lateinit var presenter: MainPresenter

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()

        moviesAdapter = MoviesAdapter {
            showDetailScreen(it)
        }

        list_characters.adapter = moviesAdapter
        list_characters.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        btn_request_character.setOnClickListener {
            presenter.fetchCharacters(txt_input_name.text.toString())
        }

        presenter.attachView(this)
    }

    private fun showDetailScreen(characterId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }

    override fun onMoviesResult(movies: List<Movie>) {
            moviesAdapter.moviesList = movies
            moviesAdapter.notifyDataSetChanged()
    }


    override fun showError(message: String) {
       Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }
}
