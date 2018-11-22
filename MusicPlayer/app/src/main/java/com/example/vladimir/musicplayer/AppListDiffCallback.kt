package com.example.vladimir.musicplayer

import android.support.v7.util.DiffUtil

class AppListDiffCallback : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Track, newItem: Track) = oldItem.name == newItem.name
}
