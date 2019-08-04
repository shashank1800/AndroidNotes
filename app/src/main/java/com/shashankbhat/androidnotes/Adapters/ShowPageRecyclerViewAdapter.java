package com.shashankbhat.androidnotes.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shashankbhat.androidnotes.Objects.ShowPageObject;
import com.shashankbhat.androidnotes.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.github.kbiakov.codeview.CodeView;

public class ShowPageRecyclerViewAdapter extends RecyclerView.Adapter<ShowPageRecyclerViewAdapter.ViewHolder>{

    private ArrayList<ShowPageObject> showPageObjects;
    private Context context;
    private int time=1000;

    public ShowPageRecyclerViewAdapter(ArrayList<ShowPageObject> showPageObjects) {
        this.showPageObjects = showPageObjects;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,content;
        ImageView imageView;
        CodeView codeView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            content = itemView.findViewById(R.id.content);
            imageView = itemView.findViewById(R.id.imageView);
            codeView = itemView.findViewById(R.id.codeView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_show_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(showPageObjects.get(position).getTitleText().compareTo("")==0)
            holder.titleText.setVisibility(View.GONE);
        holder.titleText.setText(showPageObjects.get(position).getTitleText());
        ReadText readText = new ReadText(holder.content,null);
        readText.execute(showPageObjects.get(position).getContentUrl());
        if(showPageObjects.get(position).getRawDataUrl().endsWith(".txt")){
            ReadText readCode = new ReadText(null,holder.codeView);
            readCode.execute(showPageObjects.get(position).getRawDataUrl());
            holder.imageView.setVisibility(View.GONE);
        }
        else {
            holder.codeView.setVisibility(View.GONE);
            Glide.with(context).load(showPageObjects.get(position).getRawDataUrl()).into(holder.imageView);
        }

        animate(holder,time);
        time +=300;

    }

    private void animate(RecyclerView.ViewHolder holder, int time){
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator xTranslation = ObjectAnimator.ofFloat(holder.itemView, View.TRANSLATION_X, 2000f, 0);
        xTranslation.setDuration(time);

        animatorSet.playTogether(xTranslation);
        animatorSet.setDuration(time);
        animatorSet.start();
    }

    @Override
    public int getItemCount() {
        return showPageObjects == null ? 0 : showPageObjects.size();
    }
}

class ReadText extends AsyncTask<String, Void, String>{

    private TextView textVw;
    private CodeView codeVw;
    private boolean isCode = false;
    ReadText(TextView content,CodeView codeVw) {
        this.textVw = content;
        this.codeVw = codeVw;
        if(content == null)
            isCode = true;

    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection httpURLConnection;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;

        try {
            url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null){
                result.append(line).append("\n");
                line = bufferedReader.readLine();
            }

        }catch (Exception ignored){}
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(isCode)
            codeVw.setCode(s);
        else
            textVw.setText(s);
    }
}