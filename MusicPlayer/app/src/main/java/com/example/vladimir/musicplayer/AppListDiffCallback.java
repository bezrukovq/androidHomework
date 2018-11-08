package com.example.vladimir.musicplayer;

import android.support.v7.util.DiffUtil;

public class AppListDiffCallback extends DiffUtil.ItemCallback<Track> {

    @Override
    public boolean areItemsTheSame(Track oldItem, Track newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(Track oldItem, Track newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

}
