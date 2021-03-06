package splichus.com.newsapp.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.R;
import splichus.com.newsapp.adapter.ArticleAdapter;
import splichus.com.newsapp.fragment.DetailsFragment;
import splichus.com.newsapp.service.ArticleService;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.persistency.Database;
import splichus.com.newsapp.service.ArticlesListener;

public class MainActivity extends AppCompatActivity implements ArticlesListener, ArticleAdapter.ListClickListener {

    private static final String TAG = "MainActivity";

    @Inject
    ArticleService articleService;
    @Inject
    Database database;

    @Nullable
    @BindView(R.id.dual_details)
    FrameLayout dualDetails;
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindDrawable(R.drawable.downloaded)
    Drawable downloaded;
    @BindDrawable(R.drawable.not_downloaded)
    Drawable notDownloaded;

    ArticleAdapter adapter;
    Menu menu;
    boolean down;
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        articleService.setActivity(this);
        if (dualDetails != null) {
            twoPane = true;
        }
        adapter = new ArticleAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        articleService.getFromAPI();
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
        switch (item.getItemId()) {
            case R.id.toolbar_settings:
                goToSettings();
                return true;
            case R.id.toolbarfilter:
                openSortDialog();
                return true;
            case R.id.toolbar_downloaded:
                if (down) {
                    articleService.getFromAPI();
                } else {
                    articleService.getFromDB();
                }
            default: return super.onOptionsItemSelected(item);
        }
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
            arrow.setIcon(downloaded);
        } else {
            arrow.setIcon(notDownloaded);
        }
    }

    @Override
    public void onListClicked(Article article) {
        if (!twoPane) {
            goToDetails(article);
        } else {
            openDetailsFragment(article);
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

    private void goToDetails(Article article) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(Constants.ARTICLE, article);
        startActivity(intent);
    }

    private void openDetailsFragment(Article article) {
        Bundle url = new Bundle();
        url.putSerializable(Constants.ARTICLE, article);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(url);
        getSupportFragmentManager().beginTransaction().replace(R.id.dual_details, fragment).commit();
    }
}
