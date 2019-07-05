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
import com.shashankbhat.androidnotes.R;
import com.shashankbhat.androidnotes.ShowPage;

import java.util.ArrayList;


public class ContentPageRecyclerViewAdapter extends RecyclerView.Adapter<ContentPageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<PageContentObject> pageContentObjects;
    private Context context;

    public ContentPageRecyclerViewAdapter(Context context, ArrayList<PageContentObject> pageContentObjects) {
        this.context = context;
        this.pageContentObjects = pageContentObjects;
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
            Intent intent = new Intent(context, ShowPage.class);
            intent.putExtra("PAGE_TITLE","Toast");
            intent.putExtra("PAGE_CONTENT_URL", pageContentObjects.get(getLayoutPosition()).getUrl());
            context.startActivity(intent);
        }
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
        return pageContentObjects == null ? 0 :pageContentObjects.size();
    }
}