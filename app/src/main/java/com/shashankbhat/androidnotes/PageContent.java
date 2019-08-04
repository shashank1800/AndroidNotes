package com.shashankbhat.androidnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.shashankbhat.androidnotes.Adapters.ContentPageRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.PageContentAsyncTask;
import com.shashankbhat.androidnotes.Objects.PageContentObject;
import com.shashankbhat.androidnotes.Utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class PageContent extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private RecyclerView pageContentRecyclerView;
    private LinearLayoutManager lLayoutManager;
    @SuppressLint("StaticFieldLeak")
    public static ContentPageRecyclerViewAdapter mPageContentRecAdapter;
    public static ArrayList<PageContentObject> pageContentObjects;
    private String url,pageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_content);

        toolbar = findViewById(R.id.page_content_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        context = this;

        Bundle bundle = getIntent().getExtras();
        pageTitle = bundle.getString(Constants.PAGE_TITLE);
        url = bundle.getString(Constants.URL_STRING);
        getSupportActionBar().setTitle(pageTitle);

        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        pageContentRecyclerView = findViewById(R.id.pageContentRecyclerView);
        pageContentRecyclerView.setHasFixedSize(true);
        pageContentRecyclerView.setLayoutManager(lLayoutManager);

        pageContentObjects = new ArrayList<>();

        PageContentAsyncTask downloadData = new PageContentAsyncTask();
        downloadData.execute(url);

        mPageContentRecAdapter = new ContentPageRecyclerViewAdapter(pageContentObjects);
        pageContentRecyclerView.setAdapter(mPageContentRecAdapter);

        if(url.isEmpty())
            Toast.makeText(this,"Build in Progress",Toast.LENGTH_SHORT).show();

    }
}
