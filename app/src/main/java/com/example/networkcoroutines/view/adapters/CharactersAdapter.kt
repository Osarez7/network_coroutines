package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.example.networkcoroutines.common.loadImage
import com.squareup.picasso.Picasso
import com.example.networkcoroutines.network.Character

class CharactersAdapter(private val listener: (Long) -> Unit) : RecyclerView.Adapter<CharacterViewHolder>() {

    var charactersList : List<Character>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_marvel_character, parent, false)

        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  charactersList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(charactersList?.get(position), listener)
}


class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgCharacter = itemView.findViewById<ImageView>(R.id.img_marvel_character)
    private val txtCharacterName = itemView.findViewById<TextView>(R.id.txt_character_name)


    fun bind(character: Character?, listener: (Long) -> Unit){

        character?.let { ch ->
            imgCharacter.loadImage("${ch.thumbnail.path}.${ch.thumbnail.extension}")
            txtCharacterName.text = ch.name

            itemView.setOnClickListener{
                listener(ch.id)
            }
        }
    }


}


