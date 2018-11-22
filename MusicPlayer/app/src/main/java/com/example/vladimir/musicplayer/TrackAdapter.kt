package com.example.vladimir.musicplayer

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

open class TrackAdapter (diffCallback: DiffUtil.ItemCallback<Track>, private var callback: Callback) : ListAdapter<Track, TrackAdapter.TrackHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return TrackHolder(view)
    }

    override fun onBindViewHolder(viewHolder: TrackHolder, position: Int) {
        viewHolder.id = position
        viewHolder.itemView.setOnClickListener { callback.songClick(viewHolder.id) }
        viewHolder.tvName.text = getItem(position).name
        viewHolder.description.text = getItem(position).singer
    }

    inner class TrackHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvName: TextView
        internal var description: TextView
        internal var id: Int = 0

        init {
            tvName = itemView.findViewById(R.id.tv_name)
            description = itemView.findViewById(R.id.tv_description)
        }
    }
}
