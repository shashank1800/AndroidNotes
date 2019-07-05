package com.shashankbhat.androidnotes.AsynkTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.shashankbhat.androidnotes.Objects.PageContentObject;
import com.shashankbhat.androidnotes.PageContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageContentAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        StringBuilder result= new StringBuilder();
        URL url;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        InputStreamReader inputStreamReader;

        try {
            url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStream.read();
            while (data!=-1){
                char currentChar = (char)data;
                result.append(currentChar);
                data = inputStreamReader.read();
            }

        }catch (Exception e){}
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        JSONObject json;

        try {
            json = new JSONObject(result);
            Log.i("URl Conn" , String.valueOf(json.length()));

            for (int index=0;index<json.length();index++){
                String dataString = json.getString(String.valueOf(index));
                PageContent.pageContentObjects.add(new PageContentObject(dataString));
            }
            PageContent.mPageContentRecAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
