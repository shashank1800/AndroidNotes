package com.shashankbhat.androidnotes.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.MainActivity;
import com.shashankbhat.androidnotes.Objects.HomeObject;
import com.shashankbhat.androidnotes.PageContent;
import com.shashankbhat.androidnotes.R;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>{

    private ArrayList<HomeObject> homeObjects;
    private Context context;

    public HomeRecyclerViewAdapter(Context context, ArrayList<HomeObject> homeObjects) {
        this.context = context;
        this.homeObjects = homeObjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(homeObjects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(homeObjects == null)
            return 0;
        else
            return homeObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView ;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PageContent.class);
            intent.putExtra("STRING_URL", MainActivity.homeObjects.get(getLayoutPosition()).getUrl());
            context.startActivity(intent);
        }
    }
}
