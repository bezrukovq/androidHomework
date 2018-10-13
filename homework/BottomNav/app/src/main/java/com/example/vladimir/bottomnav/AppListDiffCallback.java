package com.example.vladimir.bottomnav;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class AppListDiffCallback extends DiffUtil.ItemCallback<App> {

    @Override
    public boolean areItemsTheSame(App oldItem, App newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(App oldItem, App newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

}
