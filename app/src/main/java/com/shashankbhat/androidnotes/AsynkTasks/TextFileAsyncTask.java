package com.shashankbhat.androidnotes.AsynkTasks;

import android.os.AsyncTask;


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

            String line = bufferedReader.readLine();
            while (line!=null){
                texts.append(line);
                line = bufferedReader.readLine();
            }

        } catch (Exception ignored) { }
        return null;
    }
}
