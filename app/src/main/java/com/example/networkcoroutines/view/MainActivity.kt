package com.example.networkcoroutines.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.view.adapters.MarvelCharactersAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService




class MainActivity : AppCompatActivity(), MainView, CharacterEventListener {


    private var presenter: MainPresenter? = null

    private var marvelCharactersAdapter: MarvelCharactersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.networkcoroutines.R.layout.activity_main)

        presenter = MainPresenter()

        marvelCharactersAdapter = MarvelCharactersAdapter(this)

        list_characters.adapter = marvelCharactersAdapter
        list_characters.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        txt_input_name.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter?.searchCharacters(txt_input_name.text.toString())
                true
            } else {
                false
            }
        }


        btn_request_character.setOnClickListener {
            closeKeyboard()
            presenter?.searchCharacters(txt_input_name.text.toString())


        }

        presenter?.attachView(this)
    }

    private fun closeKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(txt_input_name.windowToken, 0)
    }


    override fun onFetchCharacters(characters: List<Character>?) {

        if (characters.isNullOrEmpty()) {
            Toast.makeText(this, "No resultas found", Toast.LENGTH_SHORT).show()
        }

        marvelCharactersAdapter?.charactersList = characters
        marvelCharactersAdapter?.notifyDataSetChanged()
    }


    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onCharacterSelected(characterId: Long?) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(CharacterDetailActivity.EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
