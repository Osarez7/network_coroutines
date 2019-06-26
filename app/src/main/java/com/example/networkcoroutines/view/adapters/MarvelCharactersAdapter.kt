package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.squareup.picasso.Picasso
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.view.CharacterEventListener

class MarvelCharactersAdapter(var characterEventListener: CharacterEventListener) : RecyclerView.Adapter<MarvelCharactersViewHolder>() {

    var charactersList : List<Character>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharactersViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_marvel_character, parent, false)

        return MarvelCharactersViewHolder(view, characterEventListener)
    }

    override fun getItemCount(): Int {
       return  charactersList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MarvelCharactersViewHolder, position: Int) {
        holder.bind(charactersList?.get(position))
    }
}


class MarvelCharactersViewHolder(
    itemView: View,
    var characterEventListener: CharacterEventListener
): RecyclerView.ViewHolder(itemView) {
    var characterId: Long? = null
    val imgCharacter = itemView.findViewById<ImageView>(R.id.img_marvel_character)
    val txtCharacterName = itemView.findViewById<TextView>(R.id.txt_character_name)
    val txtDescription = itemView.findViewById<TextView>(R.id.txt_description)

    init {
        itemView.setOnClickListener{
            characterEventListener.onCharacterSelected(characterId)
        }
    }

    fun bind(character: Character?){

        Picasso.get()
            .load(character?.thumbnail?.path + "." + character?.thumbnail?.extension)
            .into(imgCharacter)

        txtCharacterName.text = character?.name
        txtDescription.text = character?.description
        characterId  = character?.id

    }
}