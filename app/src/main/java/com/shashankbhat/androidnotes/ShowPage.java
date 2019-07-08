package com.shashankbhat.androidnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Adapters.ShowPageRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.ShowPageAsyncTask;
import com.shashankbhat.androidnotes.Objects.ShowPageObject;

import java.util.ArrayList;

public class ShowPage extends AppCompatActivity {
    private Toolbar toolbar;
    private Context context;
    private RecyclerView showPageRecyclerView;
    private LinearLayoutManager lLayoutManager;
    @SuppressLint("StaticFieldLeak")
    public static ShowPageRecyclerViewAdapter mShowPageRecyclerViewAdapter;
    public static ArrayList<ShowPageObject> showPageObjects;
    private String url,pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_page);

        Intent intent = getIntent();
        pageTitle = intent.getStringExtra("PAGE_TITLE");
        url = intent.getStringExtra("PAGE_CONTENT_URL");

        toolbar = findViewById(R.id.show_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(pageTitle);

        context = this;

        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        showPageRecyclerView = findViewById(R.id.showPageRecyclerView);
        showPageRecyclerView.setHasFixedSize(true);
        showPageRecyclerView.setLayoutManager(lLayoutManager);

        showPageObjects = new ArrayList<>();

        ShowPageAsyncTask downloadData = new ShowPageAsyncTask();
        downloadData.execute(url);

        mShowPageRecyclerViewAdapter = new ShowPageRecyclerViewAdapter(context,showPageObjects);
        showPageRecyclerView.setAdapter(mShowPageRecyclerViewAdapter);
    }

}
