package splichus.com.newsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import splichus.com.newsapp.R;
import splichus.com.newsapp.adapter.RecyclerAdapter;
import splichus.com.newsapp.api.service.NewsAPI;
import splichus.com.newsapp.service.ArticleService;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.model.Settings;
import splichus.com.newsapp.persistency.Database;
import splichus.com.newsapp.service.ArticlesProvider;

public class MainActivity extends AppCompatActivity implements ArticlesProvider {

    private static final String TAG = "MainActivity";

    @Inject
    NewsAPI api;
    @Inject
    Settings settings;
    @Inject
    Database database;

    ArticleService articleService;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Context ctx;
    Menu menu;
    Boolean down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        articleService = new ArticleService(this, settings, database, api);
        recyclerView = findViewById(R.id.main_recycler_view);
        adapter = new RecyclerAdapter(this, database);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ctx = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: started");
        articleService.getFromAPI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: finished");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_settings){
            goToSettings();
        } else if(item.getItemId() == R.id.toolbar_downloaded) {
            if (!down) {
                articleService.getFromDB();
            } else {
                articleService.getFromCache();
            }
        } else {
            openSortDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArticles(List<Article> articles) {
        adapter.setArticles(articles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDownloaded(Boolean bool) {
        MenuItem arrow = menu.findItem(R.id.toolbar_downloaded);
        down = bool;
        if (bool){
            arrow.setIcon(getResources().getDrawable(R.drawable.downloaded));
        } else {
            arrow.setIcon(getResources().getDrawable(R.drawable.not_downloaded));
        }
    }

    private void goToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void openSortDialog(){
        SortDialog sortDialog = new SortDialog();
        sortDialog.show(getSupportFragmentManager(),"sortDialog");
    }
}
