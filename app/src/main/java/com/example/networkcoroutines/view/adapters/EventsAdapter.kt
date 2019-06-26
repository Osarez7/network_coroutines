package com.example.networkcoroutines.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcoroutines.R
import com.example.networkcoroutines.network.Event
import com.squareup.picasso.Picasso

class EventsAdapter: RecyclerView.Adapter<EventsViewHolder>() {

    var eventsLists : List<Event>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  eventsLists?.size ?: 0
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(eventsLists?.get(position))
    }
}


class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val imgEVent = itemView.findViewById<ImageView>(R.id.img_event)
    val txtEventTitle = itemView.findViewById<TextView>(R.id.txt_event_title)

    fun bind(event: Event?){

        Picasso.get()
            .load(event?.thumbnail?.path + "." + event?.thumbnail?.extension)
            .resize(200, 200)
            .centerCrop()
            .into(imgEVent)

        txtEventTitle.text = event?.title

    }
}