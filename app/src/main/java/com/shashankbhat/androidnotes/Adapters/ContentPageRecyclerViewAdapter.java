package com.shashankbhat.androidnotes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Objects.PageContentObject;
import com.shashankbhat.androidnotes.PageContent;
import com.shashankbhat.androidnotes.R;

import java.util.ArrayList;


public class ContentPageRecyclerViewAdapter extends RecyclerView.Adapter<ContentPageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<PageContentObject> pageContentObjects;
    private Context context;

    public ContentPageRecyclerViewAdapter(Context context, ArrayList<PageContentObject> pageContentObjects) {
        this.context = context;
        this.pageContentObjects = pageContentObjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(pageContentObjects.get(position).getHeading());
    }

    @Override
    public int getItemCount() {
        if(pageContentObjects == null)
            return 0;
        else
            return pageContentObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PageContent.class);
            context.startActivity(intent);
        }
    }

}