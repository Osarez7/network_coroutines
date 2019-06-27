package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.example.networkcoroutines.common.loadImage
import com.example.networkcoroutines.view.models.Movie

class MoviesAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    var moviesList : List<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(moviesList?.get(position), listener)
}


class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imgMovie = itemView.findViewById<ImageView>(R.id.img_movie)
    private val txtMovieTitle = itemView.findViewById<TextView>(R.id.txt_title)

    fun bind(movie: Movie?, listener: (Int) -> Unit){

        movie?.let { m ->
            if(m.posterUrl.isNotEmpty()) imgMovie.loadImage(m.posterUrl)
            txtMovieTitle.text = m.title
            itemView.setOnClickListener{
                listener(m.id)
            }
        }
    }


}


