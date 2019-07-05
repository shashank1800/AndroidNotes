package com.shashankbhat.androidnotes.AsynkTasks;

import android.os.AsyncTask;

import com.shashankbhat.androidnotes.Adapters.TextFileAsyncTask;
import com.shashankbhat.androidnotes.Objects.ShowPageObject;
import com.shashankbhat.androidnotes.ShowPage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowPageAsyncTask extends AsyncTask<String, Void, String> {

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

            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                result.append(line);
            }

        }catch (Exception ignored){}
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        JSONObject json;

        TextFileAsyncTask contentText = new TextFileAsyncTask();
        contentText.execute();

        try {
            json = new JSONObject(result);

            for (int index=0;index<json.length();index++){
                String heading = json.getJSONObject(String.valueOf(index)).getString("heading");
                String content = json.getJSONObject(String.valueOf(index)).getString("content");
                String rawData = json.getJSONObject(String.valueOf(index)).getString("rawData");
                ShowPage.showPageObjects.add(new ShowPageObject(heading,content,rawData));
            }
            ShowPage.mShowPageRecyclerViewAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
