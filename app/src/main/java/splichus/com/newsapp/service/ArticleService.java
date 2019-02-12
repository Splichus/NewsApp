package splichus.com.newsapp.service;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import splichus.com.newsapp.BuildConfig;
import splichus.com.newsapp.Constants;
import splichus.com.newsapp.api.model.APIResponse;
import splichus.com.newsapp.api.model.CustomCallback;
import splichus.com.newsapp.api.service.NewsAPI;
import splichus.com.newsapp.model.Article;
import splichus.com.newsapp.model.Settings;
import splichus.com.newsapp.persistency.Database;

public class ArticleService {

    List<Article> articles;
    ArticlesProvider activity;
    Settings settings;
    Database database;
    NewsAPI api;

    public ArticleService(ArticlesProvider activity, Settings settings, Database database, NewsAPI api) {
        this.settings = settings;
        this.articles = new ArrayList<>();
        this.activity = activity;
        this.database = database;
        this.api = api;
    }

    public void getFromAPI() {
        articles.clear();
        if (settings.getApis().get(Constants.NEWSAPI_URL)) {
            api.getEverything(BuildConfig.API_KEY, 20, "android").enqueue(new CustomCallback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    articles.addAll(response.body().getArticles());
                    activity.onArticles(articles);
                    activity.onDownloaded(false);
                }
            });
        }
    }

    public void getFromDB() {
        activity.onArticles(database.articleDAO().getAllArticles());
        activity.onDownloaded(true);
    }
    public void getFromCache() {
        activity.onArticles(articles);
        activity.onDownloaded(false);
    }

    public List<Article> sortByAuthor() {
        Collections.sort(articles, new SortByAuthor());
        return articles;
    }

    public List<Article> sortByDate() {
        Collections.sort(articles, new SortByDate());
        return articles;
    }

    class SortByAuthor implements Comparator<Article>
    {
        @Override
        public int compare(Article article1, Article article2) {
            return article1.getAuthor().compareTo(article2.getAuthor());
        }
    }
    class SortByDate implements Comparator<Article> {

        @Override
        public int compare(Article article1, Article article2) {
            Date dateArticle1 = null;
            Date dateArticle2 = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm'Z'");

            try {
                dateArticle1 = dateFormat.parse(article1.getPublishedAt());
                dateArticle2 = dateFormat.parse(article2.getPublishedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateArticle1.compareTo(dateArticle2);
        }
    }

    public List<Article> getArticles() {
        return articles;
    }
}
