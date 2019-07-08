package com.shashankbhat.androidnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.shashankbhat.androidnotes.Adapters.HomeRecyclerViewAdapter;
import com.shashankbhat.androidnotes.AsynkTasks.DownloadAsyncTask;
import com.shashankbhat.androidnotes.Objects.HomeObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private Context context;
    private DrawerLayout drawerLayout;
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager lLayoutManager;
    @SuppressLint("StaticFieldLeak")
    public static HomeRecyclerViewAdapter mHomeRecAdapter;
    public static ArrayList<HomeObject> homeObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainRecyclerView =  findViewById(R.id.mainRecyclerView);
        mainRecyclerView.setHasFixedSize(true);
        lLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecyclerView.setLayoutManager(lLayoutManager);

        homeObjects = new ArrayList<>();

        DownloadAsyncTask downloadData = new DownloadAsyncTask();
        downloadData.execute("https://raw.githubusercontent.com/shashank1800/Android-Notes/master/HomePage.json");

        mHomeRecAdapter = new HomeRecyclerViewAdapter(context,homeObjects);
        mainRecyclerView.setAdapter(mHomeRecAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_feedback_text:
                showFeedbackDialog();
                break;
            case R.id.nav_shareapp:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, I'am learning android with this great app. No ads and Free!! ");
                intent.setType("text/plain");
                try {
                    intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                } catch (ActivityNotFoundException ignored) { }
                startActivity(intent);
                break;
            case R.id.nav_about:
                showAboutDevDialog();
                break;
            case R.id.rate_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showFeedbackDialog() {
        final ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.activity_feedback, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Feedback");
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText feedback_text = dialogView.findViewById(R.id.feedback_text);
                if(feedback_text.getText().toString().compareTo("")==0)
                    Snackbar.make(findViewById(R.id.linearLayout), "Please enter feedback text", Snackbar.LENGTH_SHORT).show();
                else {
                    Snackbar.make(findViewById(R.id.linearLayout), "Thanks for your Feedback!", Snackbar.LENGTH_SHORT).show();
                    /*String uniqueID = UUID.randomUUID().toString();
                    String feedbackText = feedback_text.getText().toString();

                    Map<String, Object> feedback = new HashMap<>();
                    feedback.put("feedbackText", feedbackText);

                    db = FirebaseFirestore.getInstance();
                    db.collection("Feedback").document(uniqueID).set(feedback);

                    InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);*/
                }
            }
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAboutDevDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.activity_about_dev, viewGroup, false);

        ImageView dev_photo = dialogView.findViewById(R.id.photo);
        Glide.with(context)
                .load(R.drawable.my_photo)
                .apply(RequestOptions.circleCropTransform())
                .into(dev_photo);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        dialogView.findViewById(R.id.fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlFb="https://www.facebook.com/shashankbhat114";
                String appFb="fb://facewebmodal/f?href="+urlFb;

                try {
                    if (isAppInstalled(context, "com.facebook.orca") || isAppInstalled(context, "com.facebook.katana")
                            || isAppInstalled(context, "com.example.facebook") || isAppInstalled(context, "com.facebook.android")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appFb)));
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)));
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        });

        dialogView.findViewById(R.id.ln).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLn="https://www.linkedin.com/in/shashank-bhat-924b1bb9/";
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlLn));
                    intent.setPackage("com.linkedin.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlLn)));
                }

            }
        });

        dialogView.findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appIs="http://instagram.com/shashank_bhat__";
                String urlIs="http://instagram.com/_u/shashank_bhat__";

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(appIs));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlIs)));
                }
            }
        });
    }
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
