package com.example.networkcoroutines.common

import android.widget.ImageView
import com.example.networkcoroutines.network.MovieTmdb
import com.example.networkcoroutines.network.MovieTrakt
import com.example.networkcoroutines.view.models.Movie
import com.example.networkcoroutines.view.models.MovieDetail
import com.uwetrottmann.tmdb2.entities.Images
import java.security.MessageDigest
import java.text.NumberFormat
import java.util.*

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}


fun Double.formatCurrency() = NumberFormat.getCurrencyInstance(Locale.US).format(this)


fun ImageView.loadImage(imageUrl: String){
    com.squareup.picasso.Picasso.get()
        .load(imageUrl)
        .into(this)
}

fun MovieTrakt.toMovie(posterUrl: String): Movie{
   return  Movie(ids.tmdb, title, overview, posterUrl)
}


fun MovieTmdb.toMovieDetail(posterUrl: String): MovieDetail{

    return  MovieDetail(id,
        title,
        overview,
        posterUrl,
        popularity,
        revenue,
        runtime,
        vote_average)
}

fun Images.getPoster(): String{
   return if(!posters.isNullOrEmpty()){
       "${Constants.IMAGES_BASE_URL}${posters?.get(0)?.file_path!!}"
    }else{
        ""
    }
}