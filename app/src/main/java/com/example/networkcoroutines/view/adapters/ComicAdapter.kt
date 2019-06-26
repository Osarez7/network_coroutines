package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.example.networkcoroutines.network.Comic
import com.squareup.picasso.Picasso

class ComicAdapter: RecyclerView.Adapter<ComicAdapterViewHolder>() {

    var comicsLists : List<Comic>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_comic, parent, false)

        return ComicAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  comicsLists?.size ?: 0
    }

    override fun onBindViewHolder(holder: ComicAdapterViewHolder, position: Int) {
        holder.bind(comicsLists?.get(position))
    }
}


class ComicAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val imgComic = itemView.findViewById<ImageView>(R.id.img_comic)
    val txtTitle = itemView.findViewById<TextView>(R.id.txt_comic_title)

    fun bind(comic: Comic?){
        Picasso.get()
            .load(comic?.thumbnail?.path + "." + comic?.thumbnail?.extension)
            .into(imgComic)
        txtTitle.text = comic?.title

    }
}