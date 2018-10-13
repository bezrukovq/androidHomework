package com.example.vladimir.bottomnav;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class AppListDiffCallback extends DiffUtil.ItemCallback<App> {
    private List<App> mOldList;
    private List<App> mNewList;

    public AppListDiffCallback(List<App> mOldList, List<App> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public boolean areItemsTheSame(App oldItem, App newItem) {
        return oldItem.getRate() == newItem.getRate();
    }

    @Override
    public boolean areContentsTheSame(App oldItem, App newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public Object getChangePayload(App oldItem, App newItem) {
        return super.getChangePayload(oldItem, newItem);
    }
}
