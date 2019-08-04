package com.shashankbhat.androidnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shashankbhat.androidnotes.Adapters.ShowPageRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.ShowPageAsyncTask;
import com.shashankbhat.androidnotes.Objects.ShowPageObject;
import com.shashankbhat.androidnotes.Utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

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

        Bundle bundle = getIntent().getExtras();
        pageTitle = bundle.getString(Constants.PAGE_TITLE);
        url = bundle.getString(Constants.URL_STRING);

        toolbar = findViewById(R.id.show_page_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(pageTitle);

        context = this;

        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        showPageRecyclerView = findViewById(R.id.showPageRecyclerView);
        showPageRecyclerView.setHasFixedSize(true);
        showPageRecyclerView.setLayoutManager(lLayoutManager);

        showPageObjects = new ArrayList<>();

        ShowPageAsyncTask downloadData = new ShowPageAsyncTask();
        downloadData.execute(url);

        mShowPageRecyclerViewAdapter = new ShowPageRecyclerViewAdapter(showPageObjects);
        showPageRecyclerView.setAdapter(mShowPageRecyclerViewAdapter);

        if(url.isEmpty())
            Toast.makeText(this,"Build in Progress",Toast.LENGTH_SHORT).show();

    }

}
