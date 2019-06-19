package com.example.networkcoroutines.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.squareup.picasso.Picasso
import com.example.networkcoroutines.network.Character

class MarvelCharactersAdapter: RecyclerView.Adapter<MarvelCharactersViewHolder>() {

    var charactersList : List<Character>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharactersViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_marvel_character, parent, false)

        return MarvelCharactersViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  charactersList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MarvelCharactersViewHolder, position: Int) {
       val character : Character? = charactersList?.get(position)

        Picasso.get()
            .load(character?.thumbnail?.path + "." + character?.thumbnail?.extension)
            .resize(200, 200)
            .centerCrop()
            .into(holder.imgCharacter)

        holder.txtCharacterName.text = character?.name
    }
}


class MarvelCharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val imgCharacter = itemView.findViewById<ImageView>(R.id.img_marvel_character)
    val txtCharacterName = itemView.findViewById<TextView>(R.id.txt_character_name)
}