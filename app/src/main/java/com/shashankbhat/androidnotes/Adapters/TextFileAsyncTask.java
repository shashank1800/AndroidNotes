package com.shashankbhat.androidnotes.Adapters;

import android.os.AsyncTask;

import com.shashankbhat.androidnotes.AsynkTasks.ShowPageAsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextFileAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        StringBuilder texts = new StringBuilder();

        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while (line!=null){
                line = bufferedReader.readLine();
                texts.append(line);
            }

        } catch (Exception ignored) { }
        return null;
    }
}
