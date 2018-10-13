package com.example.vladimir.bottomnav;


import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends ListAdapter<App, AppAdapter.AppHolder> {
    private ArrayList<App> myList;

    protected AppAdapter(@NonNull DiffUtil.ItemCallback<App> diffCallback, ArrayList<App> myList) {
        super(diffCallback);
        this.myList = myList;
    }

    public void update(ArrayList<App> list) {
        myList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new AppHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder viewHolder, int position) {
        viewHolder.tvName.setText(myList.get(position).getName());
        viewHolder.description.setText(myList.get(position).getContext());
        switch (myList.get(position).getImgID()) {
            case 0:
                viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.chrome));
                break;
            case 1:
                viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.realm));
                break;
            case 2:
                viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.andr));
                break;
            case 3:
                viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.idea));
                break;
        }
    }

    public class AppHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView description;
        ImageView icon;
        View mView;

        public AppHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvName = mView.findViewById(R.id.tv_name);
            description = mView.findViewById(R.id.tv_description);
            icon = mView.findViewById(R.id.iv_icon);
        }
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
