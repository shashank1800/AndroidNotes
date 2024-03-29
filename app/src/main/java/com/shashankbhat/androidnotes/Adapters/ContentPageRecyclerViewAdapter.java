package com.shashankbhat.androidnotes.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Objects.PageContentObject;
import com.shashankbhat.androidnotes.R;
import com.shashankbhat.androidnotes.ShowPage;
import com.shashankbhat.androidnotes.Utils.Constants;

import java.util.ArrayList;


public class ContentPageRecyclerViewAdapter extends RecyclerView.Adapter<ContentPageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<PageContentObject> pageContentObjects;
    private Context context;
    private int time = 700;

    public ContentPageRecyclerViewAdapter(ArrayList<PageContentObject> pageContentObjects) {
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
            Bundle bundle = new Bundle();
            bundle.putString(Constants.PAGE_TITLE,pageContentObjects.get(getLayoutPosition()).getHeading());
            bundle.putString(Constants.URL_STRING, pageContentObjects.get(getLayoutPosition()).getUrl());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_page_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(pageContentObjects.get(position).getHeading());
        animate(holder,time);
        time +=100;
    }

    private void animate(RecyclerView.ViewHolder holder, int time){
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator xTranslation = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_Y, 1000f, 0);
        xTranslation.setDuration(time);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.itemView, View.ALPHA, 0, 1);
        alpha.setDuration(time);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(holder.itemView, View.ROTATION_X, 50, 0);
        rotate.setDuration(time);

        animatorSet.playTogether(xTranslation,alpha,rotate);
        animatorSet.setDuration(time);
        animatorSet.start();
    }

    @Override
    public int getItemCount() {
        return pageContentObjects == null ? 0 :pageContentObjects.size();
    }
}