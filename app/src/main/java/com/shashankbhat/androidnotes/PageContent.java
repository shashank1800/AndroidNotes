package com.shashankbhat.androidnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.shashankbhat.androidnotes.Adapters.ContentPageRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.PageContentAsyncTask;
import com.shashankbhat.androidnotes.Objects.PageContentObject;

import java.util.ArrayList;

public class PageContent extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private RecyclerView pageContentRecyclerView;
    private LinearLayoutManager lLayoutManager;
    @SuppressLint("StaticFieldLeak")
    public static ContentPageRecyclerViewAdapter mPageContentRecAdapter;
    public static ArrayList<PageContentObject> pageContentObjects;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_content);

        toolbar = findViewById(R.id.page_content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        url = getIntent().getStringExtra("STRING_URL");

        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        pageContentRecyclerView = findViewById(R.id.pageContentRecyclerView);
        pageContentRecyclerView.setHasFixedSize(true);
        pageContentRecyclerView.setLayoutManager(lLayoutManager);

        pageContentObjects = new ArrayList<>();

        PageContentAsyncTask downloadData = new PageContentAsyncTask();
        downloadData.execute(url);

        mPageContentRecAdapter = new ContentPageRecyclerViewAdapter(context,pageContentObjects);
        pageContentRecyclerView.setAdapter(mPageContentRecAdapter);
    }
}
