package com.example.vladimir.recyclerview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<City> myList;

    public MyAdapter(ArrayList<City> myList) {
        this.myList = myList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        final TextView name;
        final TextView description;
        ImageView icon;
        RelativeLayout cl;
        name = viewHolder.mView.findViewById(R.id.tv_name);
        description = viewHolder.mView.findViewById(R.id.tv_description);
        icon = viewHolder.mView.findViewById(R.id.iv_icon);
        cl = viewHolder.mView.findViewById(R.id.cl_item);
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.mView.getContext(), ItemInfo.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("description", description.getText().toString());
                viewHolder.mView.getContext().startActivity(intent);
            }
        });
        name.setText(myList.get(i).getName());
        description.setText(myList.get(i).getContext());
        switch (i % 4) {
            case 0:
                icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.chrome));
                break;
            case 1:
                icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.realm));
                break;
            case 2:
                icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.andr));
                break;
            case 3:
                icon.setImageDrawable(ContextCompat.getDrawable(viewHolder.mView.getContext(), R.drawable.idea));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
