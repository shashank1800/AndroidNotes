package com.shashankbhat.androidnotes.Adapters;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashankbhat.androidnotes.MainActivity;
import com.shashankbhat.androidnotes.Objects.HomeObject;
import com.shashankbhat.androidnotes.PageContent;
import com.shashankbhat.androidnotes.R;
import com.shashankbhat.androidnotes.ShowPage;
import com.shashankbhat.androidnotes.Utils.Constants;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>{

    private ArrayList<HomeObject> homeObjects;
    private Context context;
    private int time = 700;


//    public HomeRecyclerViewAdapter(ArrayList<HomeObject> homeObjects) {
//        this.homeObjects = homeObjects;
//    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView iconImage;
        CardView background;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            iconImage = itemView.findViewById(R.id.iconImage);
            background = itemView.findViewById(R.id.background);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PageContent.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.URL_STRING,homeObjects.get(getLayoutPosition()).getUrl());
            bundle.putString(Constants.PAGE_TITLE, homeObjects.get(getLayoutPosition()).getHeading());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.background.setCardBackgroundColor(Color.parseColor(homeObjects.get(position).getBackground()));
        holder.textView.setText(homeObjects.get(position).getHeading());
        Glide.with(context).load(homeObjects.get(position).getIconUrl()).into(holder.iconImage);
        animate(holder,time,position);
        time +=100;
    }

    private void animate(RecyclerView.ViewHolder holder, int time,int position){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator xTranslation;
        ObjectAnimator alpha;
        if(position%2 ==0) {

            xTranslation = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_X, 1200f, 0);
            xTranslation.setDuration(time);

            alpha = ObjectAnimator.ofFloat(holder.itemView, View.ALPHA, 0, 1);
            alpha.setDuration(time);
        }
        else {
            xTranslation = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_X, -1200f, 0);
            xTranslation.setDuration(time);

            alpha = ObjectAnimator.ofFloat(holder.itemView, View.ALPHA, 0, 1);
            alpha.setDuration(time);
        }

        animatorSet.playTogether(xTranslation,alpha);
        animatorSet.setDuration(time);
        animatorSet.start();
    }

    @Override
    public int getItemCount() {
        return homeObjects == null ? 0 :homeObjects.size();
    }

    public void setHomeObjects(ArrayList<HomeObject> homeObjects) {
        this.homeObjects = homeObjects;
        notifyDataSetChanged();
    }
}
