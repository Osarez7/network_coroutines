package com.example.networkcoroutines.common

import android.widget.ImageView
import java.security.MessageDigest

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