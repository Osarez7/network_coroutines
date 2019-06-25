package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.example.networkcoroutines.common.loadImage
import com.example.networkcoroutines.network.Comic
import com.squareup.picasso.Picasso

class ComicsAdapter : RecyclerView.Adapter<ComicViewHolder>() {

    var comicsList: List<Comic>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_comic, parent, false)

        return ComicViewHolder(view)
    }

    override fun getItemCount() = comicsList?.size ?: 0
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) = holder.bind(comicsList?.get(position))
}


class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imgComic = itemView.findViewById<ImageView>(R.id.img_comic)
    private val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)

    fun bind(comic: Comic?) {
        comic?.let {
            imgComic.loadImage(it.thumbnail.path + "." + it.thumbnail.extension)
            txtTitle.text = it.title
        }
    }

}