package com.example.vladimir.musicplayer;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TrackAdapter extends ListAdapter<Track, TrackAdapter.TrackHolder> {

    Callback callback;

    protected TrackAdapter(@NonNull DiffUtil.ItemCallback<Track> diffCallback, Callback callback) {
        super(diffCallback);
        this.callback = callback;
    }

    @NonNull
    @Override
    public TrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new TrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackHolder viewHolder, int position) {
        viewHolder.id = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.songClick(viewHolder.id);
            }
        });
        viewHolder.tvName.setText(getItem(position).getName());
        viewHolder.description.setText(getItem(position).getSinger());
    }

    public class TrackHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView description;
        int id;

        public TrackHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}
