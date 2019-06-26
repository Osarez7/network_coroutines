package com.example.networkcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkcoroutines.R
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.Event
import com.example.networkcoroutines.view.adapters.ComicAdapter
import com.example.networkcoroutines.view.adapters.EventsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailView {


    var presenter: CharacterDetailPresenter? = null
    private var eventsAdapter: EventsAdapter? = null
    private var comicsAdapter: ComicAdapter? = null

    companion object {
        const val EXTRA_CHARACTER_ID = "character_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        presenter = CharacterDetailPresenter()
        presenter?.attachView(this)

        val characterId = intent.extras.getLong(EXTRA_CHARACTER_ID)

        Log.d("======DETAIL", "id $characterId")
        presenter?.fetchCharacter(characterId)


        eventsAdapter = EventsAdapter()
        comicsAdapter = ComicAdapter()

        list_comics.adapter = comicsAdapter
        list_comics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        list_events.adapter = eventsAdapter
        list_events.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onCharacterDetailResult(character: Character) {

        Picasso.get()
            .load(character?.thumbnail?.path + "." + character?.thumbnail?.extension)
            .resize(200, 200)
            .centerCrop()
            .into(img_character)

        txt_name.text = character.name
        txt_description.text = character.description
    }

    override fun updateComics(comics: List<Comic>) {
        comicsAdapter?.comicsLists = comics
        comicsAdapter?.notifyDataSetChanged()
    }

    override fun updateEvents(events: List<Event>) {
        Log.d("====UPDATE EVENTS","${events.size}")
        eventsAdapter?.eventsLists = events
        eventsAdapter?.notifyDataSetChanged()
    }


    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }


}
