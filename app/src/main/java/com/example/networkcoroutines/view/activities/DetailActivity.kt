package com.example.networkcoroutines.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkcoroutines.R
import com.example.networkcoroutines.common.loadImage
import com.example.networkcoroutines.view.presenters.DetailPresenter
import com.example.networkcoroutines.view.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import android.widget.Toast
import com.example.networkcoroutines.common.formatCurrency
import com.example.networkcoroutines.view.models.MovieDetail


class DetailActivity : AppCompatActivity(), DetailView {


    private lateinit var presenter: DetailPresenter

    companion object {
        const val EXTRA_CHARACTER_ID = "extra_character_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter()
        presenter.attachView(this)

        val characterId = intent.getIntExtra(EXTRA_CHARACTER_ID, 0)
        presenter.fetchCharacterDetail(characterId)
    }


    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }


    override fun onMovierDetailResult(movie: MovieDetail) {
        img_character.loadImage(movie.posterUrl)
        txt_name.text = movie.title
        txt_description.text = movie.overview
        txt_revenue.text = movie.revenue.formatCurrency()
        txt_runtime.text = movie.runtime.toString()
        txt_vote_average.text = movie.vote_average.toString()
    }

}
