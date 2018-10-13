package com.example.vladimir.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<City> myList;
    private CallMain callMain;

    public MyAdapter(ArrayList<City> myList, Context context) {
        this.myList = myList;
        callMain = (CallMain) context;
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
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMain.callback(viewHolder.tvName.getText().toString(), viewHolder.description.getText().toString());
            }
        });
        viewHolder.tvName.setText(myList.get(i).getName());
        viewHolder.description.setText(myList.get(i).getContext());
        switch (i % 4) {
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

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView description;
        ImageView icon;
        View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvName = mView.findViewById(R.id.tv_name);
            description = mView.findViewById(R.id.tv_description);
            icon = mView.findViewById(R.id.iv_icon);
        }
    }
}
