package com.example.vladimir.bottomnav;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AppAdapter extends ListAdapter<App, AppAdapter.AppHolder> {

    protected AppAdapter(@NonNull DiffUtil.ItemCallback<App> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new AppHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder viewHolder, int position) {
        viewHolder.tvName.setText(getItem(position).getName());
        viewHolder.description.setText(getItem(position).getContext());
        viewHolder.icon.setImageResource(getItem(position).getImgID());
    }

    public class AppHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView description;
        ImageView icon;
        View view;

        public AppHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = view.findViewById(R.id.tv_name);
            description = view.findViewById(R.id.tv_description);
            icon = view.findViewById(R.id.iv_icon);
        }
    }
}
