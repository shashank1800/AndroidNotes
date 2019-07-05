package com.shashankbhat.androidnotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Objects.ShowPageObject;
import com.shashankbhat.androidnotes.R;

import java.util.ArrayList;

public class ShowPageRecyclerViewAdapter extends RecyclerView.Adapter<ShowPageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<ShowPageObject> showPageObjects;
    private Context context;

    public ShowPageRecyclerViewAdapter(Context context, ArrayList<ShowPageObject> showPageObjects) {
        this.context = context;
        this.showPageObjects = showPageObjects;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,content;
        ImageView imageView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            content = itemView.findViewById(R.id.content);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_show_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleText.setText(showPageObjects.get(position).getTitleText());
        holder.content.setText(showPageObjects.get(position).getContentUrl());
    }

    @Override
    public int getItemCount() {
        if(showPageObjects == null)
            return 0;
        else
            return showPageObjects.size();
    }


}
