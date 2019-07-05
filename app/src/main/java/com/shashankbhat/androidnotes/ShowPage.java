package com.shashankbhat.androidnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Adapters.ShowPageRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.PageContentAsyncTask;
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
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_page);

        toolbar = findViewById(R.id.page_content_toolbar);
        setSupportActionBar(toolbar);

        context = this;
        url = getIntent().getStringExtra("STRING_URL");

        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        showPageRecyclerView = findViewById(R.id.pageContentRecyclerView);
        showPageRecyclerView.setHasFixedSize(true);
        showPageRecyclerView.setLayoutManager(lLayoutManager);

        showPageObjects = new ArrayList<>();

        PageContentAsyncTask downloadData = new PageContentAsyncTask();
        downloadData.execute(url);

        mShowPageRecyclerViewAdapter = new ShowPageRecyclerViewAdapter(context,showPageObjects);
        showPageRecyclerView.setAdapter(mShowPageRecyclerViewAdapter);
    }

}
