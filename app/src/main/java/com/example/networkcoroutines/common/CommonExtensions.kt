package com.example.networkcoroutines.common

import android.widget.ImageView
import com.example.networkcoroutines.network.Character
import com.example.networkcoroutines.network.Comic
import com.example.networkcoroutines.network.MarvelResponse
import com.example.networkcoroutines.view.models.CharacterDetail
import java.security.MessageDigest

private const val FIRST_RESULT_INDEX = 0

fun String.toMD5(): String {
    // toByteArray: default is Charsets.UTF_8 - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/to-byte-array.html
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}


fun ImageView.loadImage(imageUrl: String){
    com.squareup.picasso.Picasso.get()
        .load(imageUrl)
        .into(this)
}

fun Character.toCharacterDetail(comics: List<Comic>): CharacterDetail {
    return CharacterDetail(id, name, description, thumbnail, comics)
}


fun MarvelResponse<Character>.getSingleCharacter(): Character = data.results[FIRST_RESULT_INDEX]